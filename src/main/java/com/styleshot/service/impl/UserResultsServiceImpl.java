package com.styleshot.service.impl;

import com.styleshot.dao.UserResultsDao;
import com.styleshot.domain.UserResults;
import com.styleshot.service.UserResultsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
public class UserResultsServiceImpl implements UserResultsService {


    @Autowired
    public UserResultsDao userResultsDao;


    @Override
    public void add(UserResults userResult) {
        userResultsDao.save(userResult);
    }

    @Override
    public void update(UserResults userResult) {
        userResultsDao.save(userResult);
    }

    @Override
    public void delete(UserResults userResult) {
        userResultsDao.remove(userResult);
    }

    @Override
    public UserResults findUserResultById(Long id) {
        return userResultsDao.find(id);
    }

    @Override
    public List<UserResults> getAllUsersResults() {
        return userResultsDao.findAll();
    }

    @Override
    public List<UserResults> getAllUserResultsByUserId(Long userId) {
        return userResultsDao.findAllResulsByUserId(userId);
    }
}
