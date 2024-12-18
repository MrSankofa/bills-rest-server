package com.clean_slate.bills_rest_server.repository;

import com.clean_slate.bills_rest_server.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
  User findByUsername(String username);
}