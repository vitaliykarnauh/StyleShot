package com.checkdoc.service.impl;

import com.checkdoc.dao.RoleDao;
import com.checkdoc.domain.Role;
import com.checkdoc.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public void add(Role role) {
        roleDao.save(role);
    }

    @Override
    public void update(Role role) {
        roleDao.save(role);
    }

    @Override
    public void delete(Role role) {
        roleDao.remove(role);
    }

    @Override
    public Role findRoleById(Long id) {
        return roleDao.find(id);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleDao.findAll();
    }
}
