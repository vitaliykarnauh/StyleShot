package com.checkdoc.dao;

import com.checkdoc.domain.User;
import com.googlecode.genericdao.dao.hibernate.GenericDAO;

public interface UserDao extends GenericDAO<User, Long> {

    User findByUserName(String userName);
}
