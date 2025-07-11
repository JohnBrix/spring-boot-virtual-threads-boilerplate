package com.virtual.threads.controller;

import com.virtual.threads.entity.User;
import com.virtual.threads.mapper.DtoToUserMapper;
import com.virtual.threads.mapper.HttpUserResponseMapper;
import com.virtual.threads.model.*;
import com.virtual.threads.repository.UserRepository;
import com.virtual.threads.service.UserService;
import com.virtual.threads.util.UriUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.virtual.threads.constant.UserConstant.*;

/**
 * package com.virtual.threads.controller; /**
 *
 * @author <John Brix Pomoy>
 * @version $Id: UserController.java, v 0.1 2025-05-23 1:38 AM John Brix Pomoy Exp $$
 */
@Slf4j
@RestController
@RequestMapping("/api/user/v1")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private HttpUserResponseMapper httpUserResponseMapper;

    @Autowired
    private DtoToUserMapper dtoToUserMapper;

    @PostMapping("/create")
    public ResponseEntity<HttpUserResponse> createUser(@RequestBody HttpUserRequest httpUserRequest) {
        log.info(HTTP_REQUEST,httpUserRequest);

        if (!UriUtil.validateRequest(httpUserRequest)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return registerUser(httpUserRequest);
    }

    private ResponseEntity<HttpUserResponse> registerUser(HttpUserRequest httpUserRequest) {
        try {

            //Register User
            userService.registerUser(dtoToUserMapper.dtoToUser(httpUserRequest));

            //Build Success Response
            HttpUserResponse response = httpUserResponseMapper.buildOkResponse();
            log.info(HTTP_RESPONSE, response);

            //Success ResponseEntity
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (IllegalArgumentException i) {
            log.error(ERROR, i.getMessage());

            //Failed ResponseEntity
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (Exception e) {
            log.error(ERROR, e.getMessage());

            //Uncheck Exception default handling
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
