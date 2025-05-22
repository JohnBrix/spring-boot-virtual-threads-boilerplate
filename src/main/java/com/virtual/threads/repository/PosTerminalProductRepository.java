package com.virtual.threads.repository;

import com.virtual.threads.entity.PosTerminalProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * package com.virtual.threads.repository; /**
 *
 * @author <John Brix Pomoy>
 * @version $Id: PosTerminalProductRepository.java, v 0.1 2025-05-23 1:35 AM John Brix Pomoy Exp $$
 */
@Repository
public interface PosTerminalProductRepository extends JpaRepository<PosTerminalProduct,Long> {
}
