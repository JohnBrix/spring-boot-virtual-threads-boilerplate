package com.virtual.threads.service;

import com.virtual.threads.entity.Product;
import com.virtual.threads.model.HttpProductRequest;
import com.virtual.threads.model.HttpProductResponse;
import org.springframework.validation.BindingResult;

/**
 * package com.virtual.threads.service; /**
 *
 * @author <John Brix Pomoy>
 * @version $Id: ProductService.java, v 0.1 2025-05-23 2:55 AM John Brix Pomoy Exp $$
 */
public interface ProductService {
    HttpProductResponse addProduct(HttpProductRequest httpProductRequest, Long adminId, BindingResult bindingResult);
}
