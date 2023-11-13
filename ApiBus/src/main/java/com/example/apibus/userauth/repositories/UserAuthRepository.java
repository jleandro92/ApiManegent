package com.example.apibus.userauth.repositories;

import com.example.apibus.userauth.models.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserAuthRepository extends JpaRepository<UserAuth, String> {
    UserDetails findByLogin(String login);
}
