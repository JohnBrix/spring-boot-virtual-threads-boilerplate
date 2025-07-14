package com.virtual.threads.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.virtual.threads.entity.Product;
import com.virtual.threads.entity.User;
import com.virtual.threads.mapper.DtoToUserMapper;
import com.virtual.threads.mapper.HttpUserResponseMapper;
import com.virtual.threads.model.HttpUserRequest;
import com.virtual.threads.model.HttpUserResponse;
import com.virtual.threads.model.Result;
import com.virtual.threads.repository.UserRepository;
import com.virtual.threads.service.UserService;
import com.virtual.threads.util.UriUtil;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static com.virtual.threads.constant.UserConstant.RESULT_DESCRIPTION_SUCCESS;
import static com.virtual.threads.constant.UserConstant.RESULT_MESSAGE_SUCCESS;
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
 * @version $Id: UserControllerTest.java, v 0.1 2025-07-11 5:47 PM John Brix Pomoy Exp $$
 */
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    @MockitoBean
    private HttpUserResponseMapper httpUserResponseMapper;

    @MockitoBean
    private DtoToUserMapper dtoToUserMapper;

    @Autowired
    private ObjectMapper objectMapper;

    public static String BASED_ENDPOINT = "/api/user/v1";

    @Test
    @SneakyThrows(Exception.class)
    public void testCreateUserThenReturnBadRequest(){
        //Assertive
        HttpUserRequest userRequest = buildUserRequest();

        // Mock static method
//        try (MockedStatic<UriUtil> mockedStatic = Mockito.mockStatic(UriUtil.class)) {
//
//            mockedStatic.when(() -> UriUtil.validateRequest(userRequest))
//                    .thenReturn(false);
//
//            // Act & Assert
//            mockMvc.perform(post(BASED_ENDPOINT + "/create")
//                            .contentType(MediaType.APPLICATION_JSON)
//                            .content(objectMapper.writeValueAsString(userRequest)))
//                    .andExpect(status().isBadRequest());
//        }
    }

    @Test
    @SneakyThrows(Exception.class)
    public void testCreateUser(){
        //Assertive
        HttpUserRequest userRequest = buildUserRequest();

        //Mock Do nothing
        doNothing().when(userService).registerUser(any(User.class));
        when(httpUserResponseMapper.buildOkResponse()).thenReturn(buildOkResponse());

//        // Mock static method
//        try (MockedStatic<UriUtil> mockedStatic = Mockito.mockStatic(UriUtil.class)) {
//
//            mockedStatic.when(() -> UriUtil.validateRequest(userRequest))
//                    .thenReturn(true);
//
//            // Act & Assert
//            mockMvc.perform(post(BASED_ENDPOINT + "/create")
//                            .contentType(MediaType.APPLICATION_JSON)
//                            .content(objectMapper.writeValueAsString(userRequest)))
//                    .andExpect(status().isOk());
//        }
    }

    @Test
    @SneakyThrows(Exception.class)
    public void testCreateUserThenThrowIllegalArgumentException(){
        //Assertive
        HttpUserRequest userRequest = buildUserRequest();

        //Mock Do nothing
        doNothing().when(userService).registerUser(any(User.class));
        when(httpUserResponseMapper.buildOkResponse()).thenThrow(new IllegalArgumentException("UNEXPECTED_ERROR"));

//        // Mock static method
//        try (MockedStatic<UriUtil> mockedStatic = Mockito.mockStatic(UriUtil.class)) {
//
//            mockedStatic.when(() -> UriUtil.validateRequest(userRequest))
//                    .thenReturn(true);
//
//            // Act & Assert
//            mockMvc.perform(post(BASED_ENDPOINT + "/create")
//                            .contentType(MediaType.APPLICATION_JSON)
//                            .content(objectMapper.writeValueAsString(userRequest)))
//                    .andExpect(status().isUnprocessableEntity());
//        }
    }

    @Test
    @SneakyThrows(Exception.class)
    public void testCreateUserThenThrowException(){
        //Assertive
        HttpUserRequest userRequest = buildUserRequest();

        //Mock Do nothing
        doNothing().when(userService).registerUser(any(User.class));
        when(httpUserResponseMapper.buildOkResponse()).thenThrow(new RuntimeException("UNEXPECTED_ERROR"));

//        // Mock static method
//        try (MockedStatic<UriUtil> mockedStatic = Mockito.mockStatic(UriUtil.class)) {
//
//            mockedStatic.when(() -> UriUtil.validateRequest(userRequest))
//                    .thenReturn(true);
//
//            // Act & Assert
//            mockMvc.perform(post(BASED_ENDPOINT + "/create")
//                            .contentType(MediaType.APPLICATION_JSON)
//                            .content(objectMapper.writeValueAsString(userRequest)))
//                    .andExpect(status().isInternalServerError());
//        }
    }


    public HttpUserResponse buildOkResponse(){
        return HttpUserResponse.builder()
                .result(Result.builder()
                        .resulStatus(true)
                        .resultCode(0)
                        .resultDescription(RESULT_DESCRIPTION_SUCCESS)
                        .resultMessage(RESULT_MESSAGE_SUCCESS)
                        .build())
                .build();
    }

    private static HttpUserRequest buildUserRequest() {
        HttpUserRequest userRequest = new HttpUserRequest();
        userRequest.setUsername("foo");
        userRequest.setPassword("bar");
        return userRequest;
    }

}
