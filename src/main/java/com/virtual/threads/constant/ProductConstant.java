package com.virtual.threads.constant;

/**
 * package com.virtual.threads.constant; /**
 *
 * @author <John Brix Pomoy>
 * @version $Id: ProductConstant.java, v 0.1 2025-05-23 3:08 AM John Brix Pomoy Exp $$
 */
public class ProductConstant {

    private ProductConstant(){

    }
    public static final String RESULT_MESSAGE_SUCCESS = "SUCCESS";
    public static final String RESULT_MESSAGE_FAILED= "FAILED";
    public static final String BAD_REQUEST = "BAD_REQUEST";
    public static final String UNPROCESSABLE_ENTITY = "UNPROCESSABLE_ENTITY";
    public static final String RESULT_DESCRIPTION_SUCCESS = "The product has been added successfully.";
    public static final String ERROR = "ERROR: {}";
    public static final String HTTP_PRODUCT_REQUEST = "{} HttpProductRequest: {}";
    public static final String HTTP_PRODUCT_RESPONSE = "{} HttpProductResponse: {}";
    public static final String PRODUCT_REQUEST = "Product Entity: {}";
    public static final String VALIDATION_ERROR_ON_FIELD = "Validation error on field '{}': {}";
    public static final Integer UNPROCESSABLE_ENTITY_CODE = 422;
    public static final Integer BAD_REQUEST_CODE = 400;
}
