package com.styleshot.service;

import com.styleshot.domain.Role;

import java.util.List;

public interface RoleService {

    void add(Role role);

    void update(Role role);

    void delete(Role role);

    Role findRoleById(Long id);

    List<Role> getAllRoles();
}
