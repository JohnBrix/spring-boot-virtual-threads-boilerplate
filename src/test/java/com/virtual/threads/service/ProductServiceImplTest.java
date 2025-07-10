package com.virtual.threads.service;

import com.virtual.threads.entity.Product;
import com.virtual.threads.entity.User;
import com.virtual.threads.model.Role;
import com.virtual.threads.repository.ProductRepository;
import com.virtual.threads.repository.UserRepository;
import com.virtual.threads.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


/**
 * package com.virtual.threads.service; /**
 *
 * @author <John Brix Pomoy>
 * @version $Id: ProductServiceImplTest.java, v 0.1 2025-06-24 11:25 PM John Brix Pomoy Exp $$
 */

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    public void testAddProduct(){
        long adminId = 1L;

        //Entity Response
        User user = new User();
        user.setRole(Role.ADMIN);
        user.setId(adminId);
        user.setUsername("foo");
        user.setPassword("bar");

        //Mock
        Mockito.when(userRepository.findById(adminId))
                .thenReturn(Optional.of(user));

        //Entity Product
        Product product = new Product();
        product.setId(1L);
        product.setName("foo");
        product.setPrice(150.5);
        product.setCreatedAt(LocalDateTime.now());

            assertDoesNotThrow(() -> {
                productService.addProduct(product, adminId);
            });

    }

    @Test
    public void testAddProductThrowError(){
        long adminId = 1L;

        Mockito.when(userRepository.findById(adminId))
                .thenReturn(Optional.empty());

        Product product = new Product();
        product.setId(1L);
        product.setName("foo");
        product.setPrice(150.5);
        product.setCreatedAt(LocalDateTime.now());

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            productService.addProduct(product, adminId);        }
        );

        // Optionally check exception message
        assertEquals("USER_ID_NOT_FOUND", exception.getMessage());

    }
}
