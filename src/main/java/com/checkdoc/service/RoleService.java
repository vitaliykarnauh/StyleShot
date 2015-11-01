package com.checkdoc.service;

import com.checkdoc.domain.Role;
import com.checkdoc.domain.User;

import java.util.List;

public interface RoleService {

    void add(Role role);

    void update(Role role);

    void delete(Role role);

    Role findRoleById(Long id);

    List<Role> getAllRoles();
}
