package com.eliotfgn.springsecurityjwtdemo.repositories;

import com.eliotfgn.springsecurityjwtdemo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}