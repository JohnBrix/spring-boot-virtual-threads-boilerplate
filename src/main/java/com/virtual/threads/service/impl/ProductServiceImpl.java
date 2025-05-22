package com.virtual.threads.service.impl;

import com.virtual.threads.entity.Product;
import com.virtual.threads.entity.User;
import com.virtual.threads.repository.ProductRepository;
import com.virtual.threads.repository.UserRepository;
import com.virtual.threads.service.ProductService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

    @Transactional
    @Override
    public synchronized void addProduct(Product product, Long adminId){
        //findByAdminId
        User admin = findByAdminId(adminId);
        product.setAddedBy(admin);
        productRepository.save(product);

    }

    private User findByAdminId(Long adminId) {
        return userRepository
                .findById(adminId)
                .orElseThrow(() -> new IllegalArgumentException("USER_ID_NOT_FOUND") );
    }
}
