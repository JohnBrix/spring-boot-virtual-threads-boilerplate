package com.virtual.threads.service;

import com.virtual.threads.entity.User;
import com.virtual.threads.model.HttpUserRequest;
import com.virtual.threads.model.HttpUserResponse;

/**
 * package com.virtual.threads.service; /**
 *
 * @author <John Brix Pomoy>
 * @version $Id: UserService.java, v 0.1 2025-05-23 2:00 AM John Brix Pomoy Exp $$
 */
public interface UserService {
    void registerUser(User user);
}
