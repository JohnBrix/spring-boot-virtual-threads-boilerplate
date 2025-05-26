package com.virtual.threads.entity;

import com.virtual.threads.model.PaymentMethod;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

/**
 * package com.virtual.threads.entity; /**
 *
 * @author <John Brix Pomoy>
 * @version $Id: Sales.java, v 0.1 2025-05-26 2:59 PM John Brix Pomoy Exp $$
 */
@Getter
@Setter
@Entity
public class Sales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String invoiceNumber;

    private Double totalAmount;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @CreatedDate
    private LocalDateTime createdAt;
}
