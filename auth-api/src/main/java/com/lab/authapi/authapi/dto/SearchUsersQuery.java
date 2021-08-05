package com.lab.authapi.authapi.dto;

import lombok.Data;

@Data
public class SearchUsersQuery {

    private String id;
    private String username;
    private String email;
    private String fullName;

}
