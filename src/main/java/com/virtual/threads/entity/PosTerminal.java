package com.virtual.threads.entity;

import jakarta.persistence.*;
import lombok.ToString;

import java.util.Set;

/**
 * package com.virtual.threads.entity; /**
 *
 * @author <John Brix Pomoy>
 * @version $Id: PosTerminal.java, v 0.1 2025-05-23 12:46 AM John Brix Pomoy Exp $$
 */
@ToString
@Entity
@Table(name = "pos_terminal")
public class PosTerminal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long terminalId;

    private String name;
    private String location;

//    @ManyToMany
//    @JoinTable(
//            name = "pos_terminal_product",
//            joinColumns = @JoinColumn(name = "terminal_id"),
//            inverseJoinColumns = @JoinColumn(name = "product_id")
//    )
//    private Set<Product> products;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User managedBy; // Admin who manages the terminal

    public Long getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(Long terminalId) {
        this.terminalId = terminalId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public User getManagedBy() {
        return managedBy;
    }

    public void setManagedBy(User managedBy) {
        this.managedBy = managedBy;
    }
}
