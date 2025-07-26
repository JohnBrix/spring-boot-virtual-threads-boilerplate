package com.virtual.threads.util;

import com.virtual.threads.model.HttpProductRequest;
import com.virtual.threads.model.HttpUserRequest;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static com.virtual.threads.constant.UserConstant.HTTP_REQUEST;

/**
 * package com.virtual.threads.util; /**
 *
 * @author <John Brix Pomoy>
 * @version $Id: UriUtil.java, v 0.1 2025-05-27 7:01 PM John Brix Pomoy Exp $$
 */
public class UriUtil {

    private UriUtil(){

    }

    public static URI path() {
        return ServletUriComponentsBuilder.fromCurrentRequestUri()
                .buildAndExpand()
                .toUri();
    }

    public static URI path(String path, Object... uriVariableValues) {
        return ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path(path)
                .buildAndExpand(uriVariableValues)
                .toUri();
    }

}
