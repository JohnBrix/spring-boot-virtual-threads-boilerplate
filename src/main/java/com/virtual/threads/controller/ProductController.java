package com.virtual.threads.controller;

import com.virtual.threads.adapter.KYCClient;
import com.virtual.threads.mapper.HttpKycResponseMapper;
import com.virtual.threads.model.HttpKycResponse;
import com.virtual.threads.model.HttpProductRequest;
import com.virtual.threads.model.HttpProductResponse;
import com.virtual.threads.model.HttpUserRequest;
import com.virtual.threads.service.ProductService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.virtual.threads.constant.KYCConstants.HTTP_KYC_RESPONSE;
import static com.virtual.threads.constant.KYCConstants.HTTP_USER_REQUEST;
import static com.virtual.threads.constant.ProductConstant.HTTP_PRODUCT_REQUEST;
import static com.virtual.threads.constant.ProductConstant.HTTP_PRODUCT_RESPONSE;

/**
 * package com.virtual.threads.controller; /**
 *
 * @author <John Brix Pomoy>
 * @version $Id: ProductController.java, v 0.1 2025-05-23 12:42 AM John Brix Pomoy Exp $$
 */

@Slf4j
@RestController
@RequestMapping("/api/products/v1")
public class ProductController {


    @Autowired
    private ProductService productService;

    @Autowired
    private KYCClient kycClient;

    @Autowired
    private HttpKycResponseMapper httpKycResponseMapper;


    @PostMapping("/{adminId}/add-product")
    public ResponseEntity<HttpProductResponse> createProduct(
            @Valid @RequestBody HttpProductRequest httpProductRequest,
            BindingResult bindingResult,
            @PathVariable Long adminId) {

        String generateUUID = String.valueOf(UUID.randomUUID());

        log.info(HTTP_PRODUCT_REQUEST,generateUUID, httpProductRequest);

        return addProduct(adminId, httpProductRequest, bindingResult,generateUUID);
    }

    private ResponseEntity<HttpProductResponse> addProduct(Long adminId,
                                                           HttpProductRequest httpProductRequest,
                                                           BindingResult bindingResult, String generateUUID) {

        //Add product
        HttpProductResponse httpProductResponse = productService.addProduct(httpProductRequest, adminId, bindingResult);

        log.info(HTTP_PRODUCT_RESPONSE, generateUUID,httpProductResponse);

        //Response Success
        return ResponseEntity.ok(httpProductResponse);
    }

    @PostMapping("/kyc")
    public ResponseEntity<HttpKycResponse> getKyc(@RequestBody HttpUserRequest httpUserRequest, BindingResult bindingResult) {
        log.info(HTTP_USER_REQUEST, httpUserRequest);

        //Get KYC external api
        HttpKycResponse response = kycClient.getKyc(httpUserRequest,bindingResult);
        log.info(HTTP_KYC_RESPONSE, response);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
