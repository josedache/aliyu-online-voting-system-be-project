package com.aliyu.ism.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findUserByIdAndPassword(String id, String password);
    List<User> findByRole(UserRole role);
}
