package com.virtual.threads.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

/**
 * package com.virtual.threads.entity; /**
 *
 * @author <John Brix Pomoy>
 * @version $Id: Inventory.java, v 0.1 2025-05-26 2:50 PM John Brix Pomoy Exp $$
 */
@Setter
@Getter
@Entity
@Table(indexes = {
        @Index(name = "idx_updated_at", columnList = "idx_updated_at"),
})
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Product productId;


    @Column(name = "qty_in_stock")
    private Integer quantityInStock;

    @LastModifiedDate
    private LocalDateTime updatedAt;

}
