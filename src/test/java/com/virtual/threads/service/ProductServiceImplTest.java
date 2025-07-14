package com.virtual.threads.service;

import com.virtual.threads.entity.Product;
import com.virtual.threads.entity.User;
import com.virtual.threads.mapper.DtoToProductMapper;
import com.virtual.threads.mapper.HttpProductResponseMapper;
import com.virtual.threads.model.HttpProductRequest;
import com.virtual.threads.model.Role;
import com.virtual.threads.repository.ProductRepository;
import com.virtual.threads.repository.UserRepository;
import com.virtual.threads.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BindingResult;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


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

    @Mock
    private DtoToProductMapper dtoToProductMapper;

    @Mock
    private HttpProductResponseMapper httpProductResponseMapper;

    @InjectMocks
    private ProductServiceImpl productService;

    private BindingResult bindingResultMock;

    @BeforeEach
    public void setUp() {
        bindingResultMock = mock(BindingResult.class);
    }

    @Test
    public void testAddProduct() {
        //Response
        long adminId = 1L;
        User user = buildUserResponse(adminId);

        //Http product request
        HttpProductRequest httpProductRequest = buildHttpProductRequest();

        //Entity Product
        Product product = buildProductRequest();

        //Mock
        when(userRepository.findById(adminId))
                .thenReturn(Optional.of(user));
        when(dtoToProductMapper.dtoToProduct(httpProductRequest)).thenReturn(product);
        when(bindingResultMock.hasErrors()).thenReturn(false);

        assertDoesNotThrow(() -> {
            productService.addProduct(httpProductRequest, adminId, bindingResultMock);
        });

    }


    @Test
    public void testAddProductThenReturnUserIdNotFound() {
        //Request
        Product product = buildProductRequest();
        long adminId = 1L;

        HttpProductRequest httpProductRequest = buildHttpProductRequest();

        //Mock
        when(userRepository.findById(adminId))
                .thenReturn(Optional.empty());
        when(dtoToProductMapper.dtoToProduct(httpProductRequest)).thenReturn(product);
        when(bindingResultMock.hasErrors()).thenReturn(false);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                    productService.addProduct(httpProductRequest, adminId, bindingResultMock);
                }
        );

        // Optionally check exception message
        assertEquals("USER_ID_NOT_FOUND", exception.getMessage());
    }

    private static Product buildProductRequest() {
        Product product = new Product();
        product.setId(1L);
        product.setName("foo");
        product.setPrice(150.5);
        product.setCreatedAt(LocalDateTime.now());
        return product;
    }

    public HttpProductRequest buildHttpProductRequest() {
        return HttpProductRequest.builder()
                .userId(1L)
                .productId(1L)
                .name("foo")
                .stock(1)
                .price(120.2)
                .build();
    }
    private static User buildUserResponse(long adminId) {
        User user = new User();
        user.setRole(Role.ADMIN);
        user.setId(adminId);
        user.setUsername("foo");
        user.setPassword("bar");
        return user;
    }
}
