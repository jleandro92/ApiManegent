package com.ms.notific.repositories;

import com.ms.notific.models.EmailUserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailUserRepository extends JpaRepository<EmailUserModel, Long> {

}
