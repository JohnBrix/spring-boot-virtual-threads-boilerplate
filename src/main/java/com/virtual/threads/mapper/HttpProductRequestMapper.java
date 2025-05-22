package com.virtual.threads.mapper;

import com.virtual.threads.model.HttpProductRequest;
import org.springframework.stereotype.Component;

/**
 * package com.virtual.threads.mapper; /**
 *
 * @author <John Brix Pomoy>
 * @version $Id: HttpProductRequestMapper.java, v 0.1 2025-05-23 3:20 AM John Brix Pomoy Exp $$
 */
@Component
public class HttpProductRequestMapper {

    public HttpProductRequest buildHttpRequest(HttpProductRequest httpProductRequest,Long adminId){
        httpProductRequest.setProductId(adminId);
        return httpProductRequest;
    }
}
