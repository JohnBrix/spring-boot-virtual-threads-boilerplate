package com.virtual.threads.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.virtual.threads.adapter.KYCClient;
import com.virtual.threads.advice.KycException;
import com.virtual.threads.mapper.HttpKycResponseMapper;
import com.virtual.threads.model.*;
import com.virtual.threads.service.ProductService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.BindingResult;

import static com.virtual.threads.constant.ProductConstant.RESULT_DESCRIPTION_SUCCESS;
import static com.virtual.threads.constant.ProductConstant.RESULT_MESSAGE_SUCCESS;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
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
public class ProductControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductService productService;

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
        when(kycClient.getKyc(any(), any(BindingResult.class))).thenReturn(httpKycResponse);

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
        when(kycClient.getKyc(eq(userRequest), any(BindingResult.class))).thenThrow(new KycException("UPSTREAM_ERROR", httpKycResponse));
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
    @SneakyThrows(Exception.class)
    public void testCreateProduct() {
        // Arrange
        HttpProductRequest productRequest = buildProductRequest();

        //Mock service
        when(productService.addProduct(eq(productRequest),eq(1L),any(BindingResult.class))).thenReturn(buildOkResponse());

        //Assert
            mockMvc.perform(post(BASED_ENDPOINT + "/1/add-product")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(productRequest)))
                    .andExpect(status().isOk());
    }

    // @TODO E.G of mockito inline static method.
    ////        try (MockedStatic<UriUtil> mockedStatic = Mockito.mockStatic(UriUtil.class)) {
    ////
    ////            mockedStatic.when(() -> UriUtil.validateRequest(httpProductRequestMapper.buildHttpRequest(productRequest, 1L)))
    ////                    .thenReturn(true);
    ////
    ////            // Act & Assert
    ////            mockMvc.perform(post(BASED_ENDPOINT + "/1/add-product")
    ////                            .contentType(MediaType.APPLICATION_JSON)
    ////                            .content(objectMapper.writeValueAsString(productRequest)))
    ////                    .andExpect(status().isOk());
    ////        }



    public HttpProductRequest buildProductRequest() {
        return HttpProductRequest.builder()
                .userId(1L)
                .productId(1L)
                .name("foo")
                .stock(1)
                .price(120.2)
                .build();
    }

    public HttpProductResponse buildOkResponse(){
        return HttpProductResponse.builder()
                .result(Result.builder()
                        .resulStatus(true)
                        .resultCode(0)
                        .resultDescription(RESULT_DESCRIPTION_SUCCESS)
                        .resultMessage(RESULT_MESSAGE_SUCCESS)
                        .build())
                .build();
    }


}
