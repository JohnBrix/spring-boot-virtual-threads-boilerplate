package com.virtual.threads.service;

import com.virtual.threads.entity.Product;

/**
 * package com.virtual.threads.service; /**
 *
 * @author <John Brix Pomoy>
 * @version $Id: ProductService.java, v 0.1 2025-05-23 2:55 AM John Brix Pomoy Exp $$
 */
public interface ProductService {
    void addProduct(Product user, Long adminId);
}
