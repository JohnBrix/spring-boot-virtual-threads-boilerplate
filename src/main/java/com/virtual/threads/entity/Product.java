package com.virtual.threads.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

/**
 * package com.virtual.threads.entity; /**
 *
 * @author <John Brix Pomoy>
 * @version $Id: Product.java, v 0.1 2025-05-23 12:39 AM John Brix Pomoy Exp $$
 */
@Getter
@Setter
@ToString
@Table(name = "products",
        indexes = {
                @Index(name = "idx_created_at", columnList = "createdAt")
        })
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private Double price;

    @ManyToOne
    private PosTerminal posTerminal;

    @CreatedDate
    private LocalDateTime createdAt;


}
