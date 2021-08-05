package com.lab.authapi.authapi.dto;

import lombok.Data;

@Data
public class UserView {

    private String id;
    private String email;
    private String username;
    private String fullName;

}
