package com.checkdoc.dao.impl;

import com.checkdoc.dao.DocumentDao;
import com.checkdoc.domain.Document;
import com.googlecode.genericdao.dao.hibernate.GenericDAOImpl;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
@Transactional
public class DocumentDaoImpl extends GenericDAOImpl<Document, Long> implements DocumentDao {

    @Autowired
    @Override
    public void setSessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    @Override
    public Document findDocumentByUrl(String url) {
        Criteria criteria = getSession().createCriteria(Document.class);
        criteria.add(Restrictions.eq("url", url));
        return (Document) criteria.uniqueResult();
    }
}
