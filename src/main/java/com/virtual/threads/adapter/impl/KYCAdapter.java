package com.virtual.threads.adapter.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.virtual.threads.adapter.KYCClient;
import com.virtual.threads.advice.KycException;
import com.virtual.threads.mapper.HttpKycResponseMapper;
import com.virtual.threads.model.HttpKycResponse;
import com.virtual.threads.model.HttpUserRequest;
import com.virtual.threads.model.KYCResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import static com.virtual.threads.constant.EndpointConstant.KYC;
import static com.virtual.threads.constant.ProductConstant.BAD_REQUEST;
import static com.virtual.threads.constant.ProductConstant.VALIDATION_ERROR_ON_FIELD;
import static org.springframework.http.MediaType.APPLICATION_JSON;

/**
 * package com.virtual.threads.adapter; /**
 *
 * @author <John Brix Pomoy>
 * @version $Id: WiremockServiceImpl.java, v 0.1 2025-06-04 8:59 PM John Brix Pomoy Exp $$
 */
@Slf4j
@Component
public class KYCAdapter implements KYCClient {

    public static final String HTTP_CLIENT_ERROR_EXCEPTION = "HttpClientErrorException: {}";
    public static final String DEFAULT_EXCEPTION = "Exception: {}";
    public static final String KYC_RESPONSE = "KYC Response: {}";

    @Autowired
    private RestClient restClient;

    @Autowired
    public ObjectMapper objectMapper;

    @Autowired
    private HttpKycResponseMapper httpKycResponseMapper;

    @Override
    public HttpKycResponse getKyc(@Valid HttpUserRequest httpUserRequest, BindingResult bindingResult) {

        //Validate HttpUserRequest
        validateKYCRequest(bindingResult);

        //Calling External API
        return getKYC(httpUserRequest);
    }

    private HttpKycResponse getKYC(HttpUserRequest httpUserRequest) {
        try {
            KYCResponse kycResponse =  restClient.post()
                    .uri(KYC)
                    .contentType(APPLICATION_JSON)
                    .header("Authorization", "Bearer YOUR_TOKEN_HERE")
                    .body(httpUserRequest)
                    .retrieve()
                    .body(KYCResponse.class);

            log.info(KYC_RESPONSE,kycResponse);

            return httpKycResponseMapper.buildOkResponse(kycResponse);
        } catch (HttpClientErrorException e) {
            log.info(HTTP_CLIENT_ERROR_EXCEPTION,e.getMessage());

            return validateClientErrorResponse(e);
        }
        catch (Exception e){
            log.info(DEFAULT_EXCEPTION,e.getMessage());
            return httpKycResponseMapper.buildInternalServerError();
        }
    }

    private HttpKycResponse validateClientErrorResponse(HttpClientErrorException e) {
        return switch (e.getStatusCode()) {
            case HttpStatus.UNPROCESSABLE_ENTITY       -> httpKycResponseMapper.buildGenericErrorResponse();
            default                                    -> httpKycResponseMapper.buildInternalServerError();
        };
    }

    private void validateKYCRequest(BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            bindingResult.getFieldErrors().forEach(error ->
                    log.error(VALIDATION_ERROR_ON_FIELD, error.getField(), error.getDefaultMessage())
            );

            throw new KycException(BAD_REQUEST,httpKycResponseMapper.buildGenericErrorResponse()) ;
        }
    }


}
