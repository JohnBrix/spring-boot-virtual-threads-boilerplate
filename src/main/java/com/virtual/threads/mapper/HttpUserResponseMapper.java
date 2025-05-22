package com.virtual.threads.mapper;

import com.virtual.threads.model.HttpUserResponse;
import com.virtual.threads.model.Result;
import org.springframework.stereotype.Component;

import static com.virtual.threads.constant.UserConstant.RESULT_DESCRIPTION_SUCCESS;
import static com.virtual.threads.constant.UserConstant.RESULT_MESSAGE_SUCCESS;

/**
 * package com.virtual.threads.mapper; /**
 *
 * @author <John Brix Pomoy>
 * @version $Id: PersonResponseMapper.java, v 0.1 2025-05-23 2:21 AM John Brix Pomoy Exp $$
 */
@Component
public class HttpUserResponseMapper {

    public HttpUserResponse buildOkResponse(){
        return HttpUserResponse.builder()
                .result(Result.builder()
                        .resulStatus(true)
                        .resultCode(0)
                        .resultDescription(RESULT_DESCRIPTION_SUCCESS)
                        .resultMessage(RESULT_MESSAGE_SUCCESS)
                        .build())
                .build();
    }
}
