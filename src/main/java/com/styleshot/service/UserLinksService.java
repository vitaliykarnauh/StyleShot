package com.styleshot.service;

import com.styleshot.domain.User;
import com.styleshot.domain.UserLinks;

import java.util.List;

public interface UserLinksService {

    void add(UserLinks userLink);

    void update(UserLinks userLink);

    void delete(UserLinks userLink);

    UserLinks findUserLinkById(Long id);

    List<UserLinks> getAllUsersLinks();

    List<UserLinks> getAllUserLinksByUserId(Long userId);

}
