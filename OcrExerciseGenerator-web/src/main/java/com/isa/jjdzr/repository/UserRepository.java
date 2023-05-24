package com.isa.jjdzr.repository;

import com.isa.jjdzr.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUserName(String userName);

    boolean existsByUserEmail(String userEmail);

    Optional<User> findByUserName(String userName);

    User findByUserId(Long id);

}
