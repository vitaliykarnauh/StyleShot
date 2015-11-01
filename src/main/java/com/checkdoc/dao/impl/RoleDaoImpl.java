package com.checkdoc.dao.impl;

import com.checkdoc.dao.RoleDao;
import com.checkdoc.domain.Role;
import com.googlecode.genericdao.dao.hibernate.GenericDAOImpl;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public class RoleDaoImpl extends GenericDAOImpl<Role,Long> implements RoleDao {


    @Autowired
    @Override
    public void setSessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }
}
