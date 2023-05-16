package com.smart.smart.repository;
import java.util.Optional;

import com.smart.smart.Models.ERole;
import com.smart.smart.Models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
