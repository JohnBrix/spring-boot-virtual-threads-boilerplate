package com.virtual.threads.entity;

import jakarta.persistence.*;
import lombok.ToString;

import java.util.Set;

/**
 * package com.virtual.threads.entity; /**
 *
 * @author <John Brix Pomoy>
 * @version $Id: Product.java, v 0.1 2025-05-23 12:39 AM John Brix Pomoy Exp $$
 */
@ToString
@Table(name = "products")
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double price;
    private Integer stock;

//    @ManyToMany(mappedBy = "products")
//    private Set<PosTerminal> terminals;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User addedBy; // Admin who manages products

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public User getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(User addedBy) {
        this.addedBy = addedBy;
    }
}
