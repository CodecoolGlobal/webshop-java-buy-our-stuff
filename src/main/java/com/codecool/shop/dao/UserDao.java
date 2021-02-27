package com.codecool.shop.dao;

import com.codecool.shop.model.User;

public interface UserDao {
    void add(User user);
    User find(String userName, String password);
    boolean isNameAvailable(String username);
}
