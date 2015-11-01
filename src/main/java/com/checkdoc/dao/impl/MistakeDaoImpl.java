package com.checkdoc.dao.impl;

import com.checkdoc.dao.MistakeDao;
import com.checkdoc.domain.Mistake;
import com.googlecode.genericdao.dao.hibernate.GenericDAOImpl;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public class MistakeDaoImpl extends GenericDAOImpl<Mistake, Long> implements MistakeDao {


    @Autowired
    @Override
    public void setSessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }
}
