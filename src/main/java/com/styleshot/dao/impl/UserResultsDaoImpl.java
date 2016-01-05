package com.styleshot.dao.impl;

import com.googlecode.genericdao.dao.hibernate.GenericDAOImpl;
import com.styleshot.dao.UserResultsDao;
import com.styleshot.domain.UserResults;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;



@Repository
@Transactional
public class UserResultsDaoImpl extends GenericDAOImpl<UserResults, Long> implements UserResultsDao {


    @Autowired
    @Override
    public void setSessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    @Override
    public List<UserResults> findAllResulsByUserId(Long userId) {
        Criteria criteria = getSession().createCriteria(UserResults.class);
        return criteria.add(Restrictions.eq("userId", userId)).list();
    }
}
