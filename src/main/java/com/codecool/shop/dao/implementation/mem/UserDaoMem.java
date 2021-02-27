package com.codecool.shop.dao.implementation.mem;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.DaoManager;
import com.codecool.shop.dao.DataNotFoundException;
import com.codecool.shop.dao.UserDao;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.User;
import org.mindrot.jbcrypt.BCrypt;

import java.util.ArrayList;
import java.util.List;

public class UserDaoMem implements UserDao {

    private final List<User> users = new ArrayList<>();

    @Override
    public void add(User user) {
        if (isNameAvailable(user.getName())) {
            CartDao cartDao = DaoManager.getCartDao();
            Cart cart = new Cart();
            cartDao.add(cart);

            user.setCartId(cart.getId());
            user.setId(users.size() + 1);
            users.add(user);
        }
    }

    @Override
    public User find(String name, String password) {
        return users
                .stream()
                .filter(user -> user.getName().equals(name) && BCrypt.checkpw(password, user.getPassword()))
                .findFirst()
                .orElseThrow(() -> new DataNotFoundException("No such user"));
    }

    @Override
    public boolean isNameAvailable(String name) {
        return users
                .stream()
                .map(User::getName)
                .noneMatch(username -> username.equals(name));
    }
}
