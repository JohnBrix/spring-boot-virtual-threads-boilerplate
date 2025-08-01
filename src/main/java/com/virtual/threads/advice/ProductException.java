package com.virtual.threads.advice;

import com.virtual.threads.model.HttpProductResponse;
import lombok.Getter;

/**
 * package com.virtual.threads.advice; /**
 *
 * @author <John Brix Pomoy>
 * @version $Id: ProductExceptionHandler.java, v 0.1 2025-05-27 6:12 PM John Brix Pomoy Exp $$
 */
@Getter
public class ProductException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    //Transient excluded from Serializable.
    private final transient HttpProductResponse productResponse;
    private final String apiDocsLink;
    private final Integer statusCode;

    public ProductException(String message,HttpProductResponse response,String apiDocsLink, Integer statusCode) {
        super(getResultMessage(message));
        this.productResponse = response;
        this.apiDocsLink = apiDocsLink;
        this.statusCode  = statusCode;
    }

    private static String getResultMessage(String message) {
        if (message != null) {
            return message;
        }
        return "Product error occurred";
    }
}
