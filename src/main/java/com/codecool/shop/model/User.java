package com.codecool.shop.model;


import lombok.Getter;
import lombok.Setter;
import org.mindrot.jbcrypt.BCrypt;

@Getter
public class User extends BaseModel {
    private final String name;
    private final String password;
    @Setter private int cartId;

    public User(String name, String password) {
        this.name = name;
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }
}
