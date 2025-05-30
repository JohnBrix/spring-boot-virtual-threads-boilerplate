package com.virtual.threads.entity;

import com.virtual.threads.model.Location;
import com.virtual.threads.model.Store;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * package com.virtual.threads.entity; /**
 *
 * @author <John Brix Pomoy>
 * @version $Id: PosTerminal.java, v 0.1 2025-05-23 12:46 AM John Brix Pomoy Exp $$
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "pos_terminal")
public class PosTerminal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Store storeName;
    @Enumerated(EnumType.STRING)
    private Location location;
}
