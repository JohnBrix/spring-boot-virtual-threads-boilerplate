package com.virtual.threads.util;

import com.virtual.threads.model.HttpProductRequest;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

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

    public static boolean validateRequest(HttpProductRequest httpProductRequest) {
        return switch (httpProductRequest) {
            case HttpProductRequest request when request.getName().isEmpty() -> false;
            case HttpProductRequest request when null == request.getPrice() || request.getPrice() < 0 -> false;
            default -> true;
        };
    }

}
