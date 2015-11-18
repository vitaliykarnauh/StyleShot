package com.checkdoc.dao.impl;

import com.checkdoc.dao.DirectoryDao;
import com.checkdoc.domain.Directory;
import com.googlecode.genericdao.dao.hibernate.GenericDAOImpl;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
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

    @Override
    public Directory findDirectoryByUrl(String url) {
        Criteria criteria = getSession().createCriteria(Directory.class);
        criteria.add(Restrictions.eq("url", url));
        return (Directory) criteria.uniqueResult();
    }
}
