package com.isa.jjdzr.repository;

import com.isa.jjdzr.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
