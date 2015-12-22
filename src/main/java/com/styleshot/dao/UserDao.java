package com.styleshot.dao;

import com.styleshot.domain.User;
import com.googlecode.genericdao.dao.hibernate.GenericDAO;

public interface UserDao extends GenericDAO<User, Long> {

    User findByUserName(String userName);

    User findByEmail(String email);
}
