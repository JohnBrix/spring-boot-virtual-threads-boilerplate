package com.virtual.threads.mapper;

import com.virtual.threads.model.HttpKycResponse;
import com.virtual.threads.model.KYCResponse;
import org.springframework.stereotype.Component;

/**
 * package com.virtual.threads.mapper; /**
 *
 * @author <John Brix Pomoy>
 * @version $Id: HttpKycResponseMapper.java, v 0.1 2025-06-04 9:29 PM John Brix Pomoy Exp $$
 */
@Component
public class HttpKycResponseMapper {

    public HttpKycResponse buildGenericErrorResponse(){

        return HttpKycResponse.builder()
                .description("422")
                .isSuccess(false)
                .message("UNPROCESSABLE_ENTITY")
                .status(1)
                .build();
    }

    public HttpKycResponse buildInternalServerError(){

        return HttpKycResponse.builder()
                .description("500")
                .isSuccess(false)
                .message("INTERNAL_SERVER_ERROR")
                .status(1)
                .build();
    }

    public HttpKycResponse buildOkResponse(KYCResponse kycResponse){
        return HttpKycResponse.builder()
                .description(kycResponse.getDescription())
                .isSuccess(kycResponse.getIsSuccess())
                .message(kycResponse.getMessage())
                .status(kycResponse.getStatus())
                .build();
    }


}
