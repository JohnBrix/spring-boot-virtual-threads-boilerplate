package com.virtual.threads.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.virtual.threads.adapter.KYCClient;
import com.virtual.threads.advice.KycException;
import com.virtual.threads.entity.Product;
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
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * package com.virtual.threads.controller; /**
 *
 * @author <John Brix Pomoy>
 * @version $Id: ProductControllerTest.java, v 0.1 2025-06-16 4:38 PM John Brix Pomoy Exp $$
 */
@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductService productService;

    @MockitoBean
    private DtoToProductMapper dtoToProductMapper;

    @MockitoBean
    private HttpProductResponseMapper httpProductResponseMapper;

    @MockitoBean
    private HttpProductRequestMapper httpProductRequestMapper;

    @MockitoBean
    private KYCClient kycClient;

    @MockitoBean
    private HttpKycResponseMapper httpKycResponseMapper;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String BASED_ENDPOINT = "/api/products/v1/";

    @Test
    public void testKyc() throws Exception {
        //Request
        HttpUserRequest userRequest = buildUserRequest();

        //Response
        HttpKycResponse httpKycResponse = buildKycResponse();

        //Mock
        when(kycClient.getKyc(any())).thenReturn(httpKycResponse);

        //Calling endpoint and AssertEquals to 200
        mockMvc.perform(post(BASED_ENDPOINT + "/kyc")
                        .header("X-Custom-Header", "my-header-value")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequest)))
                .andExpect(status().isOk());
    }

    @Test
    public void testKycThenReturnException() throws Exception {
        //Request
        HttpUserRequest userRequest = buildUserRequest();

        HttpKycResponse httpKycResponse = buildKycResponse();

        //Mock
        when(kycClient.getKyc(userRequest)).thenThrow(new KycException("UPSTREAM_ERROR",httpKycResponse));
        when(httpKycResponseMapper.buildGenericErrorResponse()).thenReturn(httpKycResponse);

        //Calling endpoint and AssertEquals to 200
        mockMvc.perform(post(BASED_ENDPOINT + "/kyc")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequest)))
                .andExpect(status().isUnprocessableEntity());
    }

    private static HttpKycResponse buildKycResponse() {
        HttpKycResponse httpKycResponse = HttpKycResponse.builder()
                .message("sample")
                .isSuccess(true)
                .description("sample")
                .build();
        return httpKycResponse;
    }

    private static HttpUserRequest buildUserRequest() {
        HttpUserRequest userRequest = new HttpUserRequest();
        userRequest.setUsername("foo");
        userRequest.setPassword("bar");
        return userRequest;
    }

    @Test
    public void testCreateProduct() throws Exception {
        // Arrange
        HttpProductRequest productRequest = buildProductRequest();

        // Mock service
        doNothing().when(productService).addProduct(any(Product.class), eq(1L));

        // Mock static method
        try (MockedStatic<UriUtil> mockedStatic = Mockito.mockStatic(UriUtil.class)) {

            mockedStatic.when(() -> UriUtil.validateRequest(httpProductRequestMapper.buildHttpRequest(productRequest, 1L)))
                    .thenReturn(true);

            // Act & Assert
            mockMvc.perform(post(BASED_ENDPOINT + "/1/add-product")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(productRequest)))
                    .andExpect(status().isOk());
        }
    }

    @Test
    public void testCreateProductThenReturnBadRequest() throws Exception {
        // Arrange
        HttpProductRequest productRequest = buildProductRequest();

        //Inline mock for static method
        try (MockedStatic<UriUtil> mockStatic = Mockito.mockStatic(UriUtil.class)) {
            mockStatic.when(() -> UriUtil.validateRequest(httpProductRequestMapper.buildHttpRequest(productRequest,1L))).thenReturn(false);

            when(httpProductResponseMapper.buildBadRequestResponse()).thenReturn(HttpProductResponse.builder().build());

            // Act & Assert
            mockMvc.perform(post(BASED_ENDPOINT+"/1/add-product") // Adjust endpoint if needed
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(new ObjectMapper().writeValueAsString(productRequest)))
                    .andExpect(status().isBadRequest());
        }
    }


    public HttpProductRequest buildProductRequest(){
       return HttpProductRequest.builder()
                .userId(1L)
                .productId(1L)
                .name("foo")
                .stock(1)
                .price(120.2)
                .build();
    }


}
