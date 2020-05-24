package com.aliyu.ism.user;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class UserRoleFormatter implements Formatter<UserRole> {


    @Override
    public UserRole parse(String text, Locale locale) throws ParseException {
        return getUserRole(text);
    }

    @Override
    public String print(UserRole object, Locale locale) {
        return object.name();
    }

    public UserRole getUserRole(String text) {
        return UserRole.valueOf(text);
    }
}
