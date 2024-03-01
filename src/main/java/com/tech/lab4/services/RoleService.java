package com.tech.lab4.services;

import com.tech.lab4.dao.RoleDAO;
import com.tech.lab4.entities.ERoles;
import com.tech.lab4.entities.Role;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class RoleService implements IRoleService{

    private final RoleDAO roleDAO;

}
