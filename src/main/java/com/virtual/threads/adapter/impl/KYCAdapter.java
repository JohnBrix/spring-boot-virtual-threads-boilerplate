package com.virtual.threads.adapter.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.virtual.threads.adapter.KYCClient;
import com.virtual.threads.advice.KycException;
import com.virtual.threads.mapper.HttpKycResponseMapper;
import com.virtual.threads.model.HttpKycResponse;
import com.virtual.threads.model.HttpUserRequest;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import static com.virtual.threads.constant.EndpointConstant.BASE_ENDPOINT;
import static com.virtual.threads.constant.EndpointConstant.KYC;
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

    @Autowired
    private RestClient restClient;

    @Autowired
    public ObjectMapper objectMapper;

    @Override
    public HttpKycResponse getKyc(HttpUserRequest httpUserRequest) {

        return restClient.post()
                .uri(BASE_ENDPOINT + KYC)
                .contentType(APPLICATION_JSON)
                .header("Authorization", "BEARER_TOKEN")
                .body(httpUserRequest)
                .retrieve()
                .body(HttpKycResponse.class);
    }
}
