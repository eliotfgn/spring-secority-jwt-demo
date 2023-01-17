package com.eliotfgn.springsecurityjwtdemo.repositories;

import com.eliotfgn.springsecurityjwtdemo.domain.ERole;
import com.eliotfgn.springsecurityjwtdemo.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole role);
}