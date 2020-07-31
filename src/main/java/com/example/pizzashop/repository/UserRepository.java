package com.example.pizzashop.repository;

import com.example.pizzashop.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    int countByEmail(String email);

    User findByEmailAndPasswordSha(String email, String passwordSha);

    User findByToken(String token);
}
