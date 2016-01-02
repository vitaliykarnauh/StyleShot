package com.styleshot.service.impl;

import com.styleshot.dao.UserLinksDao;
import com.styleshot.domain.UserLinks;
import com.styleshot.service.UserLinksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserLinksServiceImpl implements UserLinksService{


    @Autowired
    public UserLinksDao userLinksDao;

    @Override
    public void add(UserLinks userLink) {
        userLinksDao.save(userLink);
    }

    @Override
    public void update(UserLinks userLink) {
        userLinksDao.save(userLink);
    }

    @Override
    public void delete(UserLinks userLink) {
        userLinksDao.remove(userLink);
    }

    @Override
    public UserLinks findUserLinkById(Long id) {
        return userLinksDao.find(id);
    }

    @Override
    public List<UserLinks> getAllUsersLinks() {
        return userLinksDao.findAll();
    }

    @Override
    public List<UserLinks> getAllUserLinksByUserId(Long userId) {
        return userLinksDao.findAllLinksByUserId(userId);
    }


}
