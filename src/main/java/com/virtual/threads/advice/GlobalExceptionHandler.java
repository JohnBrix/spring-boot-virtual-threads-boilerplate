package com.virtual.threads.advice;

import com.virtual.threads.mapper.HttpProductResponseMapper;
import com.virtual.threads.util.UriUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.util.Map;

import static com.virtual.threads.constant.HandlerConstant.*;
import static com.virtual.threads.constant.ProductConstant.ERROR;

/**
 * package com.virtual.threads.advice; /**
 *
 * @author <John Brix Pomoy>
 * @version $Id: GlobalExceptionHandler.java, v 0.1 2025-05-27 6:03 PM John Brix Pomoy Exp $$
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    private HttpProductResponseMapper httpProductResponseMapper;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemDetail> handleException(Exception exception) {
        log.error(ERROR,exception.getCause());

        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setType(URI.create(API_DOCUMENTS_LINK)); //link of api specs for certain response
        problemDetail.setTitle(EXCEPTION);
        problemDetail.setDetail(exception.getMessage());
        problemDetail.setInstance(UriUtil.path());

        problemDetail.setProperties(Map.ofEntries(
                Map.entry(HTTP_PRODUCT_RESPONSE, httpProductResponseMapper.buildGenericErrorResponse())
        ));

        return new ResponseEntity<>(problemDetail, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ProblemDetail> handleIllegalArgumentException(IllegalArgumentException illegalArgumentException) {
        log.error(ERROR,illegalArgumentException.getCause());

        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        problemDetail.setType(URI.create(API_DOCUMENTS_LINK)); //link of api specs for certain response
        problemDetail.setTitle(ILLEGAL_ARGUMENT_EXCEPTION);
        problemDetail.setDetail(illegalArgumentException.getMessage());
        problemDetail.setInstance(UriUtil.path());

        problemDetail.setProperties(Map.ofEntries(
                Map.entry(HTTP_PRODUCT_RESPONSE, httpProductResponseMapper.buildGenericErrorResponse())
        ));

        return new ResponseEntity<>(problemDetail, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(ProductException.class)
    public ResponseEntity<ProblemDetail> handleProductException(ProductException productException) {
        log.error(ERROR,productException.getCause());

        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setType(URI.create(API_DOCUMENTS_LINK)); //link of api specs for certain response
        problemDetail.setTitle(PRODUCT_EXCEPTION);
        problemDetail.setDetail(productException.getMessage());
        problemDetail.setInstance(UriUtil.path());

        problemDetail.setProperties(Map.ofEntries(
                Map.entry(HTTP_PRODUCT_RESPONSE, productException.getProductResponse())
        ));


        return new ResponseEntity<>(problemDetail, HttpStatus.BAD_REQUEST);
    }


}
