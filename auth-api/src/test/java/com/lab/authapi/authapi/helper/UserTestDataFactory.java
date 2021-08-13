package com.lab.authapi.authapi.helper;

import com.lab.authapi.authapi.dto.CreateUserRequest;
import com.lab.authapi.authapi.dto.UserView;
import com.lab.authapi.authapi.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Service
public class UserTestDataFactory {

    @Autowired
    private UserService userService;

    public UserView createUser(String username,
                               String fullName,
                               String password) {
        CreateUserRequest createRequest = new CreateUserRequest();
        createRequest.setUsername(username);
        createRequest.setFullName(fullName);
        createRequest.setPassword(password);
        createRequest.setRePassword(password);
        createRequest.setEmail(String.format("%s@example.com", username));

        UserView userView = userService.create(createRequest);

        assertNotNull(userView.getId(), "User id must not be null!");
        assertEquals(fullName, userView.getFullName(), "User name update isn't applied!");
        return userView;
    }

    public UserView createUser(String username,
                               String fullName) {
        return createUser(username, fullName, "Test12345_");
    }

    public void deleteUser(String id) {
        userService.delete(new ObjectId(id));
    }
}
