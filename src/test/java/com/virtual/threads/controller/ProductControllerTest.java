package com.virtual.threads.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.virtual.threads.adapter.KYCClient;
import com.virtual.threads.mapper.DtoToProductMapper;
import com.virtual.threads.mapper.HttpKycResponseMapper;
import com.virtual.threads.mapper.HttpProductRequestMapper;
import com.virtual.threads.mapper.HttpProductResponseMapper;
import com.virtual.threads.model.HttpKycResponse;
import com.virtual.threads.model.HttpUserRequest;
import com.virtual.threads.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
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

    @Test
    public void testKyc() throws Exception {
        HttpUserRequest userRequest = new HttpUserRequest();
        userRequest.setUsername("foo");
        userRequest.setPassword("bar");

        HttpKycResponse httpKycResponse = HttpKycResponse.builder()
                .message("sample")
                .isSuccess(true)
                .description("sample")
                .build();

        String jsonRequest = objectMapper.writeValueAsString(userRequest);

        Mockito.when(kycClient.getKyc(any())).thenReturn(httpKycResponse);

        mockMvc.perform(post("/api/products/v1/kyc")
                        .header("X-Custom-Header", "my-header-value")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk());
    }
}
