package com.virtual.threads.service.impl;

import com.virtual.threads.advice.ProductException;
import com.virtual.threads.entity.Product;
import com.virtual.threads.mapper.DtoToProductMapper;
import com.virtual.threads.mapper.HttpProductResponseMapper;
import com.virtual.threads.model.HttpProductRequest;
import com.virtual.threads.model.HttpProductResponse;
import com.virtual.threads.repository.ProductRepository;
import com.virtual.threads.repository.UserRepository;
import com.virtual.threads.service.ProductService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import static com.virtual.threads.constant.HandlerConstant.USER_ID_NOT_FOUND;
import static com.virtual.threads.constant.ProductConstant.*;

/**
 * package com.virtual.threads.service.impl; /**
 *
 * @author <John Brix Pomoy>
 * @version $Id: ProductServiceImpl.java, v 0.1 2025-05-23 2:54 AM John Brix Pomoy Exp $$
 */
@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private DtoToProductMapper dtoToProductMapper;

    @Autowired
    private HttpProductResponseMapper httpProductResponseMapper;


    //Why Synchronized? I ensure if adding the product during process 1 thread a time,
    //they need to finish it before they allowed to call add product
    @Transactional
    @Override
    public synchronized HttpProductResponse addProduct(@Valid HttpProductRequest httpProductRequest,
                                                       Long adminId,
                                                       BindingResult bindingResult){

        //Validate Http Request
        validateProductRequest(bindingResult);

        //Dto to Entity Mapper
        Product product = dtoToProductMapper.dtoToProduct(httpProductRequest);
        log.info(PRODUCT_REQUEST,product);

        //Find By Admin ID and throw if USER_ID_NOT_FOUND
        findByAdminId(adminId);

        //Save product
        saveProduct(product);

        //Build HttpOkResponse
        return httpProductResponseMapper.buildOkResponse();
    }

    private void validateProductRequest(BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            bindingResult.getFieldErrors().forEach(error ->
                    log.error(VALIDATION_ERROR_ON_FIELD, error.getField(), error.getDefaultMessage())
            );

            throw new ProductException(BAD_REQUEST, httpProductResponseMapper.buildBadRequestResponse());
        }
    }

    private void findByAdminId(Long adminId) {
        userRepository
                .findById(adminId)
                .orElseThrow(() -> new IllegalArgumentException(USER_ID_NOT_FOUND));
    }

    private void saveProduct(Product product){
        productRepository.save(product);
    }


}
