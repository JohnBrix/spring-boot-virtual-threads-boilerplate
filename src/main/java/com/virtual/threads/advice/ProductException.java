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

    public ProductException(HttpProductResponse response) {
        super(getResultMessage(response));
        this.productResponse = response;
    }

    private static String getResultMessage(HttpProductResponse response) {
        if (response != null && response.getResult() != null) {
            return response.getResult().getResultMessage();
        }
        return "Product error occurred";
    }
}
