package com.virtual.threads.controller;

import com.virtual.threads.entity.User;
import com.virtual.threads.mapper.DtoToUserMapper;
import com.virtual.threads.mapper.HttpUserResponseMapper;
import com.virtual.threads.model.HttpUserRequest;
import com.virtual.threads.model.HttpUserResponse;
import com.virtual.threads.model.Role;
import com.virtual.threads.repository.UserRepository;
import com.virtual.threads.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @Autowired
    private BCryptPasswordEncoder encoder;

    //TODO: Later move this in service, controller should not put a business core layer
    @Autowired
    private UserRepository userRepository;


    //TODO: SOON TO BE FIX with spring authentication server this is for temporary.

    @PostMapping("/create")
    public ResponseEntity<HttpUserResponse> createUser(@RequestBody HttpUserRequest httpUserRequest) {

        if (!validateRequest(httpUserRequest)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return registerUser(httpUserRequest);
    }

    @PostMapping("/generate")
    public ResponseEntity<HttpUserResponse>generateBcrypt(@RequestBody HttpUserRequest httpUserRequest){
        String rawPassword = httpUserRequest.getPassword();
        String encodedPassword = encoder.encode(rawPassword);

        // Simulate checking password during login
        boolean isMatch = encoder.matches(rawPassword, encodedPassword);

        if(!isMatch){
            return ResponseEntity.unprocessableEntity().build();
        }

        User user = new User();
        user.setUsername(httpUserRequest.getUsername());
        user.setPassword(encodedPassword);
        user.setRole(Role.ADMIN);
        userRepository.save(user);

        return ResponseEntity.ok(httpUserResponseMapper.buildOkResponse());
    }

    private ResponseEntity<HttpUserResponse> registerUser(HttpUserRequest httpUserRequest) {
        try {

            //Register User
            userService.registerUser(dtoToUserMapper.dtoToUser(httpUserRequest));

            //Build Success Response
            HttpUserResponse response = httpUserResponseMapper.buildOkResponse();
            log.debug(HTTP_RESPONSE, response);

            //Success ResponseEntity
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (IllegalArgumentException i) {
            log.error(ERROR, i);

            //Failed ResponseEntity
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (Exception e) {
            log.error(ERROR, e);

            //Uncheck Exception default handling
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public Boolean validateRequest(HttpUserRequest httpUserRequest) {
        log.info(HTTP_REQUEST, httpUserRequest);

        return switch (httpUserRequest) {
            case HttpUserRequest request when request.getUsername().isEmpty() -> false;
            case HttpUserRequest request when request.getPassword().isEmpty() -> false;
            default -> true;
        };
    }
}
