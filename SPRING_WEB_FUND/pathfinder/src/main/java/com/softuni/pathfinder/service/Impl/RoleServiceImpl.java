package com.softuni.pathfinder.service.Impl;

import com.softuni.pathfinder.model.entity.RoleEntity;
import com.softuni.pathfinder.repository.RoleRepository;
import com.softuni.pathfinder.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public RoleEntity getUserRole() {
        return roleRepository.findById(3L).orElse(null);
    }
}
