package com.virtual.threads.mapper;

import com.virtual.threads.model.HttpProductResponse;
import com.virtual.threads.model.Result;
import org.springframework.stereotype.Component;

import static com.virtual.threads.constant.ProductConstant.*;

/**
 * package com.virtual.threads.mapper; /**
 *
 * @author <John Brix Pomoy>
 * @version $Id: HttpProductResponse.java, v 0.1 2025-05-23 3:03 AM John Brix Pomoy Exp $$
 */
@Component
public class HttpProductResponseMapper {

    public HttpProductResponse buildOkResponse(){
        return HttpProductResponse.builder()
                .result(Result.builder()
                        .resulStatus(true)
                        .resultCode(0)
                        .resultDescription(RESULT_DESCRIPTION_SUCCESS)
                        .resultMessage(RESULT_MESSAGE_SUCCESS)
                        .build())
                .build();
    }

    public HttpProductResponse buildGenericErrorResponse(){
            return HttpProductResponse.builder()
                    .result(Result.builder()
                            .resulStatus(false)
                            .resultCode(1)
                            .resultDescription(RESULT_MESSAGE_FAILED)
                            .resultMessage(RESULT_MESSAGE_FAILED)
                            .build())
                    .build();
        }

    public HttpProductResponse buildUnprocessableEntity(){
        return HttpProductResponse.builder()
                .result(Result.builder()
                        .resulStatus(false)
                        .resultCode(2)
                        .resultDescription(UNPROCESSABLE_ENTITY)
                        .resultMessage(UNPROCESSABLE_ENTITY)
                        .build())
                .build();
    }

    public HttpProductResponse buildBadRequestResponse(){
        return HttpProductResponse.builder()
                .result(Result.builder()
                        .resulStatus(false)
                        .resultCode(2)
                        .resultDescription(BAD_REQUEST)
                        .resultMessage(BAD_REQUEST)
                        .build())
                .build();
    }
}
