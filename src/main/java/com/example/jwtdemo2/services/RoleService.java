package com.example.jwtdemo2.services;
import com.example.jwtdemo2.models.ERole;
import com.example.jwtdemo2.models.Role;
import com.example.jwtdemo2.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RoleService {
    @Autowired
    RoleRepository repository;

    public Role createRole(ERole e){
        Role r=new Role(e);

        return repository.save(r);
    }

    public boolean findByEROLE(ERole eRole){
        return repository.existsByName(eRole);
    }
}
