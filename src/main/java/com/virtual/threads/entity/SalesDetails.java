package com.virtual.threads.entity;

import com.virtual.threads.model.TransactionType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

/**
 * package com.virtual.threads.entity; /**
 *
 * @author <John Brix Pomoy>
 * @version $Id: SalesDetails.java, v 0.1 2025-05-26 3:03 PM John Brix Pomoy Exp $$
 */
@Getter
@Setter
@Entity
@Table(indexes = {
        @Index(name = "idx_transaction", columnList = "transaction"),
        @Index(name = "idx_created_at", columnList = "createdAt")
})
public class SalesDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Product productId;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    private Long quantity;

    private String note;

    @ManyToOne
    private User user;

    @ManyToOne
    private Sales sales;

    @CreatedDate
    private LocalDateTime createdAt;

}
