package com.virtual.threads.controller;

import com.virtual.threads.advice.ProductException;
import com.virtual.threads.mapper.DtoToProductMapper;
import com.virtual.threads.mapper.HttpProductRequestMapper;
import com.virtual.threads.mapper.HttpProductResponseMapper;
import com.virtual.threads.model.HttpProductRequest;
import com.virtual.threads.model.HttpProductResponse;
import com.virtual.threads.model.Result;
import com.virtual.threads.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.virtual.threads.constant.ProductConstant.RESULT_MESSAGE_FAILED;

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


    @PostMapping("/{adminId}/add-product")
    public ResponseEntity<HttpProductResponse> createProduct(@PathVariable Long adminId,
                                                             @RequestBody HttpProductRequest httpProductRequest) {

        //Validate http request
        if(!validateRequest(httpProductRequestMapper.buildHttpRequest(httpProductRequest,adminId))){
            log.info("bad_request_trigger");
            //add the result from mapper response
             throw new ProductException(httpProductResponseMapper.buildBadRequestResponse());
        }


        return addProduct(adminId, httpProductRequest);
    }

    private boolean validateRequest(HttpProductRequest httpProductRequest){
        return switch (httpProductRequest){
            case HttpProductRequest request when request.getName().isEmpty() -> false;
            case HttpProductRequest request when null == request.getPrice() || request.getPrice() < 0   -> false;
            default -> true;
        };
    }

    private ResponseEntity<HttpProductResponse> addProduct(Long adminId, HttpProductRequest httpProductRequest) {
            //Add product
            productService.addProduct(dtoToProductMapper.dtoToProduct(httpProductRequest), adminId);
            //Response Success
            return ResponseEntity.ok(httpProductResponseMapper.buildOkResponse());
    }

}
