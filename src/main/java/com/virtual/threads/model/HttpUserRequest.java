package com.virtual.threads.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * package com.virtual.threads.model; /**
 *
 * @author <John Brix Pomoy>
 * @version $Id: HttpUserRequest.java, v 0.1 2025-05-23 1:40 AM John Brix Pomoy Exp $$
 */
@Data
public class HttpUserRequest {

    @NotBlank(message = "Username is required")
    @NotEmpty(message = "Username is required")
    private String username;

    @NotBlank(message = "Password is required")
    @NotEmpty(message = "Password is required")
    private String password;
}
