package com.mytime.forall.services;

import com.mytime.forall.domain.Role;
import com.mytime.forall.repositories.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
   @Autowired
    private RoleRepo roleRepo;
    public boolean saveRole(Role role) {
       Role roleFromDB = roleRepo.findByNamerole(role.getNamerole());

        if (roleFromDB != null) {
            return false;}
        roleRepo.save(role);
        return true;
    }
}
