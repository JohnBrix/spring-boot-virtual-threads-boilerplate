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
        log.error(ERROR,exception.getMessage());

        //Building passing generic response
        ProblemDetail problemDetail = buildGenericResponse(
                exception.getMessage(),
                API_DOCUMENTS_LINK,
                EXCEPTION,
                HttpStatus.INTERNAL_SERVER_ERROR);

        //Custom HttpResponse
        problemDetail.setProperties(Map.ofEntries(
                Map.entry(HTTP_PRODUCT_RESPONSE, httpProductResponseMapper.buildGenericErrorResponse())
        ));

        return new ResponseEntity<>(problemDetail, HttpStatus.INTERNAL_SERVER_ERROR);
    }



    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ProblemDetail> handleIllegalArgumentException(IllegalArgumentException illegalArgumentException) {
        //Building passing generic response
        ProblemDetail problemDetail = buildGenericResponse(
                illegalArgumentException.getMessage(),
                API_DOCUMENTS_LINK,
                ILLEGAL_ARGUMENT_EXCEPTION,
                HttpStatus.UNPROCESSABLE_ENTITY);

        //Custom HttpResponse
        problemDetail.setProperties(Map.ofEntries(
                Map.entry(HTTP_PRODUCT_RESPONSE, httpProductResponseMapper.buildGenericErrorResponse())
        ));

        return new ResponseEntity<>(problemDetail, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(ProductException.class)
    public ResponseEntity<ProblemDetail> handleProductException(ProductException productException) {
        //Building passing generic response
        ProblemDetail problemDetail = buildGenericResponse(
                productException.getMessage(),
                API_DOCUMENTS_LINK,
                PRODUCT_EXCEPTION,
                HttpStatus.BAD_REQUEST);

        //Custom HttpResponse
        problemDetail.setProperties(Map.ofEntries(
                Map.entry(HTTP_PRODUCT_RESPONSE, productException.getProductResponse())
        ));


        return new ResponseEntity<>(problemDetail, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(KycException.class)
    public ResponseEntity<ProblemDetail> handleKycException(KycException kycException) {
        //Building passing generic response
        ProblemDetail problemDetail = buildGenericResponse(
                kycException.getMessage(),
                API_DOCUMENTS_LINK,
                KYC_EXCEPTION,
                HttpStatus.UNPROCESSABLE_ENTITY);

        //Custom HttpResponse
        problemDetail.setProperties(Map.ofEntries(
                Map.entry(HTTP_KYC_RESPONSE, kycException.getHttpKycResponse())
        ));


        return new ResponseEntity<>(problemDetail, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    private ProblemDetail buildGenericResponse(String errorMessage,
                                               String apiDocumentsLink,
                                               String exception,
                                               HttpStatus httpStatus) {
        log.error(ERROR,errorMessage);

        ProblemDetail problemDetail = ProblemDetail.forStatus(httpStatus);
        problemDetail.setType(URI.create(apiDocumentsLink)); //link of api specs for a certain response
        problemDetail.setTitle(exception);
        problemDetail.setDetail(errorMessage);
        problemDetail.setInstance(UriUtil.path());
        return problemDetail;
    }


}
