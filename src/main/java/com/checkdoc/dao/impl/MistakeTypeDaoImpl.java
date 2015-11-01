package com.checkdoc.dao.impl;

import com.checkdoc.dao.MistakeDao;
import com.checkdoc.dao.MistakeTypeDao;
import com.checkdoc.domain.MistakeType;
import com.googlecode.genericdao.dao.hibernate.GenericDAOImpl;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public class MistakeTypeDaoImpl extends GenericDAOImpl<MistakeType, Long> implements MistakeTypeDao {


    @Autowired
    @Override
    public void setSessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }
}
