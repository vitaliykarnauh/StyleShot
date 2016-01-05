package com.styleshot.service;

import com.styleshot.domain.UserResults;

import java.util.List;

public interface UserResultsService {


    void add(UserResults userResult);

    void update(UserResults userResult);

    void delete(UserResults userResult);

    UserResults findUserResultById(Long id);

    List<UserResults> getAllUsersResults();

    List<UserResults> getAllUserResultsByUserId(Long userId);

}
