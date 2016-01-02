package com.styleshot.dao;


import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.styleshot.domain.UserLinks;

import java.util.List;

public interface UserLinksDao extends GenericDAO<UserLinks, Long> {

    List<UserLinks> findAllLinksByUserId(Long userId);
}
