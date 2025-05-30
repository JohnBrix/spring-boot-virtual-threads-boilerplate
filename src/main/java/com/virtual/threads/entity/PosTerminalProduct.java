package com.virtual.threads.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * package com.virtual.threads.entity; /**
 *
 * @author <John Brix Pomoy>
 * @version $Id: PosTerminalProduct.java, v 0.1 2025-05-23 1:32 AM John Brix Pomoy Exp $$
 */
@Getter
@Setter
@Entity
@Table(name = "pos_terminal_product")
@IdClass(PosTerminalProductId.class)
public class PosTerminalProduct {
    @Id
    @ManyToOne
    @JoinColumn(name = "terminal_id")
    private PosTerminal terminal;

    @Id
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
