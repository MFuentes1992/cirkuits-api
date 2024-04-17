package com.cirkuits.cirkuitsapi.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface UsersRepository extends JpaRepository<Users, UUID> {
    @Query("SELECT u FROM Users u WHERE u.email = ?1 AND u.active = true")
    Users findByEmail(String email);

    @Query("SELECT u FROM Users u WHERE u.email = ?1 AND u.active = false")
    Users findPendingUserByEmail(String email);
    Users findByUserName(String userName);
}
