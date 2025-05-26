package com.virtual.threads.controller;

import com.virtual.threads.mapper.DtoToProductMapper;
import com.virtual.threads.mapper.HttpProductRequestMapper;
import com.virtual.threads.mapper.HttpProductResponseMapper;
import com.virtual.threads.model.HttpProductRequest;
import com.virtual.threads.model.HttpProductResponse;
import com.virtual.threads.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

import static com.virtual.threads.constant.ProductConstant.ERROR;

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
            return ResponseEntity.badRequest()
                    .build();
        }


        return addProduct(adminId, httpProductRequest);
    }

    private Boolean validateRequest(HttpProductRequest httpProductRequest){
        return switch (httpProductRequest){
            case HttpProductRequest request when null == request.getProductId() || 0 == request.getProductId() -> false;
            case HttpProductRequest request when request.getName().isEmpty() -> false;
            case HttpProductRequest request when null == request.getPrice() || request.getPrice() < 0   -> false;
            case HttpProductRequest request when null == request.getStock() || 0 == request.getStock() -> false;
            default -> true;
        };
    }

    private ResponseEntity<HttpProductResponse> addProduct(Long adminId, HttpProductRequest httpProductRequest) {
        try{
            //Add product
            productService.addProduct(dtoToProductMapper.dtoToProduct(httpProductRequest), adminId);

            return ResponseEntity.ok(httpProductResponseMapper.buildOkResponse());
        }catch (IllegalArgumentException i){
            log.error(ERROR,i);
            return ResponseEntity.unprocessableEntity()
                    .build();
        }
        catch (Exception e){
            log.error(ERROR,e);

            return ResponseEntity.internalServerError()
                    .build();
        }
    }

}
