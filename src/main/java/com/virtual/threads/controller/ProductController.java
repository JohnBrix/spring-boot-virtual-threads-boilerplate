package com.virtual.threads.controller;

import com.virtual.threads.adapter.KYCClient;
import com.virtual.threads.advice.KycException;
import com.virtual.threads.advice.ProductException;
import com.virtual.threads.mapper.DtoToProductMapper;
import com.virtual.threads.mapper.HttpKycResponseMapper;
import com.virtual.threads.mapper.HttpProductRequestMapper;
import com.virtual.threads.mapper.HttpProductResponseMapper;
import com.virtual.threads.model.HttpKycResponse;
import com.virtual.threads.model.HttpProductRequest;
import com.virtual.threads.model.HttpProductResponse;
import com.virtual.threads.model.HttpUserRequest;
import com.virtual.threads.service.ProductService;
import com.virtual.threads.util.UriUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    private DtoToProductMapper dtoToProductMapper;

    @Autowired
    private HttpProductResponseMapper httpProductResponseMapper;

    @Autowired
    private HttpProductRequestMapper httpProductRequestMapper;

    @Autowired
    private KYCClient kycClient;

    @Autowired
    private HttpKycResponseMapper httpKycResponseMapper;


    @PostMapping("/{adminId}/add-product")
    public ResponseEntity<HttpProductResponse> createProduct(@PathVariable Long adminId,
                                                             @RequestBody HttpProductRequest httpProductRequest) {

        //Validate http request
        if (!UriUtil.validateRequest(httpProductRequestMapper.buildHttpRequest(httpProductRequest, adminId))) {
            throw new ProductException("BAD_REQUEST",httpProductResponseMapper.buildBadRequestResponse());
        }


        return addProduct(adminId, httpProductRequest);
    }

    @PostMapping("/kyc")
    public ResponseEntity<HttpKycResponse> getKyc(@RequestBody HttpUserRequest httpUserRequest) {

        log.info("HttpUser {}",httpUserRequest);

        try {
            //Get KYC egress
            HttpKycResponse response = kycClient.getKyc(httpUserRequest);
            log.info("kyc response: {}",response);

            return ResponseEntity.ok(response);
        }catch (Exception e){
            log.info(e.getMessage());
            throw new KycException(e.getMessage(),httpKycResponseMapper.buildGenericErrorResponse());
        }
    }

    private ResponseEntity<HttpProductResponse> addProduct(Long adminId, HttpProductRequest httpProductRequest) {
        //Add product
        productService.addProduct(dtoToProductMapper.dtoToProduct(httpProductRequest), adminId);
        //Response Success
        return ResponseEntity.ok(httpProductResponseMapper.buildOkResponse());
    }

}
