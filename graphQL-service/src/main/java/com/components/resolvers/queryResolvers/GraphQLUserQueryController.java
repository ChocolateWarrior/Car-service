package com.components.resolvers.queryResolvers;

import com.components.entities.User;
import com.components.services.UserService;

import java.util.List;

public class GraphQLUserQueryController {

    private UserService userService;

    public GraphQLUserQueryController(UserService userService) {
        this.userService = userService;
    }

    public List<User> getUsers(final long count) {
        return this.userService.findMultiple(count);
    }

    public User getUser(final long id) {
        return this.userService.findById(id);
    }
}
