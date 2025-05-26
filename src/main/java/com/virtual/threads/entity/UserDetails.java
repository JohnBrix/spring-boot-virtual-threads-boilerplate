package com.virtual.threads.entity;

import com.virtual.threads.model.Gender;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

/**
 * package com.virtual.threads.entity; /**
 *
 * @author <John Brix Pomoy>
 * @version $Id: UserDetails.java, v 0.1 2025-05-26 2:37 PM John Brix Pomoy Exp $$
 */
@Getter
@Setter
@Entity
@Table(name = "user_details",
        indexes = {
        @Index(name = "idx_gender", columnList = "gender"),
        @Index(name = "idx_created_at", columnList = "createdAt")
}
)
public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User userId;

    @Column(unique = true, nullable = false)
    private String email;

    private String firstName;
    private String lastName;
    private String middleName;
    private Integer age;
    private String mobtel;
    private String address;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @CreatedDate
    private LocalDateTime createdAt;
}
