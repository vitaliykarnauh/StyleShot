package com.checkdoc.dao.impl;

import com.checkdoc.dao.DirectoryDao;
import com.checkdoc.domain.Directory;
import com.googlecode.genericdao.dao.hibernate.GenericDAOImpl;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public class DirectoryDaoImpl extends GenericDAOImpl<Directory, Long> implements DirectoryDao {

    @Autowired
    @Override
    public void setSessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }
}
