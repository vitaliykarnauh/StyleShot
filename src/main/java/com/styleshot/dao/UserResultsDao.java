package com.styleshot.dao;

import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.styleshot.domain.UserResults;

import java.util.List;

public interface UserResultsDao extends GenericDAO<UserResults, Long> {

    List<UserResults> findAllResulsByUserId(Long userId);
}
