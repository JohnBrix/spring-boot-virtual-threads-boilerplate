package com.virtual.threads.entity;

import jakarta.persistence.*;

/**
 * package com.virtual.threads.entity; /**
 *
 * @author <John Brix Pomoy>
 * @version $Id: PosTerminalProduct.java, v 0.1 2025-05-23 1:32 AM John Brix Pomoy Exp $$
 */
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

    public PosTerminal getTerminal() {
        return terminal;
    }

    public void setTerminal(PosTerminal terminal) {
        this.terminal = terminal;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
