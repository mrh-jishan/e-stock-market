package com.lab.authapi.authapi.repository;

import com.lab.authapi.authapi.dto.Page;
import com.lab.authapi.authapi.dto.SearchUsersQuery;
import com.lab.authapi.authapi.model.User;

import java.util.List;

public interface UserRepoCustom {
    List<User> searchUsers(Page page, SearchUsersQuery query);
}
