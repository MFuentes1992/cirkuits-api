package com.cirkuits.cirkuitsapi.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UsersRepository extends JpaRepository<Users, UUID> {
    Users findByEmail(String email);
    Users findByUserName(String userName);
}
