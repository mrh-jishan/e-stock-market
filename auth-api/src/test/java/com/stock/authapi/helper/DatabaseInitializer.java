package com.stock.authapi.helper;

import com.stock.authapi.dto.CreateUserRequest;
import com.stock.authapi.model.Role;
import com.stock.authapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class DatabaseInitializer implements ApplicationListener<ApplicationReadyEvent> {

    protected final static List<String> usernames = List.of(
            "ada.lovelace",
            "alan.turing",
            "dennis.ritchie"
    );
    protected final static List<String> fullNames = List.of(
            "Ada Lovelace",
            "Alan Turing",
            "Dennis Ritchie"
    );
    protected final static List<String> roles = List.of(
            Role.USER_ADMIN,
            Role.USER_ADMIN,
            Role.USER_BASIC
    );

    private final String password = "Test12345_";

    private final UserService userService;


    @Autowired
    public DatabaseInitializer(UserService userService) {
        this.userService = userService;

    }


    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        for (int i = 0; i < usernames.size(); ++i) {
            CreateUserRequest request = new CreateUserRequest();
            request.setUsername(usernames.get(i));
            request.setFullName(fullNames.get(i));
            request.setPassword(password);
            request.setRePassword(password);
            request.setEmail(usernames.get(i) + "@example.com");
            request.setAuthorities(Set.of(roles.get(i)));
            userService.upsert(request);
        }
    }
}
