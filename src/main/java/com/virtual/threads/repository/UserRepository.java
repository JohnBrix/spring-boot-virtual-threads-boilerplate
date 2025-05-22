package com.virtual.threads.repository;

import com.virtual.threads.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * package com.virtual.threads.repository; /**
 *
 * @author <John Brix Pomoy>
 * @version $Id: UserRepository.java, v 0.1 2025-05-23 1:35 AM John Brix Pomoy Exp $$
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
