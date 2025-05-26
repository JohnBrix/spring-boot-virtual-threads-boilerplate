package com.virtual.threads.entity;

import com.virtual.threads.model.Role;
import com.virtual.threads.model.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

/**
 * package com.virtual.threads.entity; /**
 *
 * @author <John Brix Pomoy>
 * @version $Id: User.java, v 0.1 2025-05-23 1:04 AM John Brix Pomoy Exp $$
 */
@Getter
@Setter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private Status status; //Active or Inactive

    @Enumerated(EnumType.STRING)
    private Role role; // ADMIN or CASHIER

}
