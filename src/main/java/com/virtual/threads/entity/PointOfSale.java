package com.virtual.threads.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

/**
 * package com.virtual.threads.entity; /**
 *
 * @author <John Brix Pomoy>
 * @version $Id: PointOfSale.java, v 0.1 2025-05-26 3:48 PM John Brix Pomoy Exp $$
 */
@Getter
@Setter
@Entity
public class PointOfSale {

    @Id
    private Long id;

    @ManyToOne
    private PosTerminal posTerminal;
    @ManyToOne
    private Inventory inventory;
    @ManyToOne
    private Sales sales;

    @CreatedDate
    private LocalDateTime createdAt;
}
