package com.virtual.threads.service.impl;

import com.virtual.threads.entity.User;
import com.virtual.threads.model.HttpUserRequest;
import com.virtual.threads.model.HttpUserResponse;
import com.virtual.threads.repository.UserRepository;
import com.virtual.threads.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * package com.virtual.threads.service; /**
 *
 * @author <John Brix Pomoy>
 * @version $Id: UserServiceImpl.java, v 0.1 2025-05-23 1:59 AM John Brix Pomoy Exp $$
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public synchronized void registerUser(User user){
        userRepository.save(user);
    }
}
