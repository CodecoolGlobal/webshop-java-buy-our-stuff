package com.codecool.shop.dao.implementation.jdbc;

import com.codecool.shop.dao.*;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.User;
import com.codecool.shop.util.Error;
import com.codecool.shop.util.Util;
import lombok.Cleanup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoJDBC extends BaseJDBC implements UserDao {

    @Override
    public void add(User user) {
        String query = "INSERT INTO account (name, password, cart_id) VALUES (?, ?, ?);";

        CartDao cartDao = DaoController.getCartDao();
        Cart cart = new Cart("USD");
        cartDao.add(cart);

        try {
            @Cleanup Connection conn = getConnection();
            @Cleanup PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, user.getName());
            stmt.setString(2, user.getPassword());
            stmt.setInt(3, cart.getId());
            stmt.execute();
        } catch (SQLException e) {
            throw new DataSourceException(Error.DATABASE_IS_UNREACHABLE, e);
        }
    }

    @Override
    public User find(String username, String password) {
        String query = "SELECT id, password, cart_id FROM account WHERE name = (?)";

        try {
            @Cleanup Connection conn = getConnection();
            @Cleanup PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next() && Util.checkPassword(password, rs.getString("password"))) {
                User user = new User(username, password);
                user.setCartId(rs.getInt("cart_id"));
                user.setId(rs.getInt("id"));
                return user;
            }
        } catch (SQLException e) {
            throw new DataSourceException(Error.DATABASE_IS_UNREACHABLE, e);
        }

        throw new DataNotFoundException(Error.NO_SUCH_USER);
    }

    @Override
    public boolean isNameAvailable(String username) {
        String query = "SELECT id, password FROM account WHERE name = (?)";

        try {
            @Cleanup Connection conn = getConnection();
            @Cleanup PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return false;
            }
        } catch (SQLException e) {
            throw new DataSourceException(Error.DATABASE_IS_UNREACHABLE, e);
        }
        return true;
    }
}

