package com.virtual.threads.repository;

import com.virtual.threads.entity.PosTerminal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * package com.virtual.threads.repository; /**
 *
 * @author <John Brix Pomoy>
 * @version $Id: PosTerminalRepository.java, v 0.1 2025-05-23 12:49 AM John Brix Pomoy Exp $$
 */
@Repository
public interface PosTerminalRepository extends JpaRepository<PosTerminal,Long> {
}
