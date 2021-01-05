package com.mytime.forall.repositories;

import com.mytime.forall.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role,Integer> {
    Role findByNamerole(String nameRole);
}
