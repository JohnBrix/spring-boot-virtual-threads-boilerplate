package com.virtual.threads.mapper;

import com.virtual.threads.model.HttpKycResponse;
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
}
