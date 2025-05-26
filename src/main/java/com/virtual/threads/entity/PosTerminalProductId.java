package com.virtual.threads.entity;

import java.util.Objects;

/**
 * package com.virtual.threads.entity; /**
 *
 * @author <John Brix Pomoy>
 * @version $Id: PosTerminalProductId.java, v 0.1 2025-05-23 4:01 AM John Brix Pomoy Exp $$
 */
public class PosTerminalProductId {
    private Long terminal;  // match the PK type of PosTerminal entity
    private Long product;   // match the PK type of Product entity

    public PosTerminalProductId() {}

    public PosTerminalProductId(Long terminal, Long product) {
        this.terminal = terminal;
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PosTerminalProductId)) return false;

        //Will create terminal_id_product_id and terminal_id_terminal_id
        PosTerminalProductId that = (PosTerminalProductId) o;
        return Objects.equals(terminal, that.terminal) &&
                Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(terminal, product);
    }
}
