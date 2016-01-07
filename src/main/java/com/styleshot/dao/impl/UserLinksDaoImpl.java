package com.styleshot.dao.impl;

import com.googlecode.genericdao.dao.hibernate.GenericDAOImpl;
import com.styleshot.dao.UserLinksDao;
import com.styleshot.domain.UserLinks;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

import static org.hibernate.criterion.Restrictions.eq;

@Repository
@Transactional
public class UserLinksDaoImpl extends GenericDAOImpl<UserLinks, Long> implements UserLinksDao {


    @Autowired
    @Override
    public void setSessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    @Override
    public List<UserLinks> findAllLinksByUserId(Long userId) {
        Criteria criteria = getSession().createCriteria(UserLinks.class);
        Query query = getSession().createQuery("from UserLinks where user.id = :userId");
        query.setParameter("userId", userId);
        return query.list();
    }
}
