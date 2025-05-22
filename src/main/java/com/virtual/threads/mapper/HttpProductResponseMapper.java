package com.virtual.threads.mapper;

import com.virtual.threads.model.HttpProductResponse;
import com.virtual.threads.model.Result;
import org.springframework.stereotype.Component;

import static com.virtual.threads.constant.ProductConstant.RESULT_DESCRIPTION_SUCCESS;
import static com.virtual.threads.constant.ProductConstant.RESULT_MESSAGE_SUCCESS;

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
}
