package com.example.dn.Repository;

import com.example.dn.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Поиск пользователя по username
    User findByUsername(String username);

    // Поиск пользователя по email (если понадобится)
    User findByEmail(String email);
}
