package com.stock.authapi.repository;

import com.stock.authapi.dto.Page;
import com.stock.authapi.dto.SearchUsersQuery;
import com.stock.authapi.model.User;

import java.util.List;

public interface UserRepoCustom {
    List<User> searchUsers(Page page, SearchUsersQuery query);
}
