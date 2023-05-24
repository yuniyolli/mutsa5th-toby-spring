package com.example.springbootgradle.dao;

import com.example.springbootgradle.domain.User;

import java.sql.Connection;
import java.sql.SQLException;

public class UserDaoTest {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ConnectionMaker connectionMaker = new DConnectionMaker();
        UserDao userDao = new UserDao(connectionMaker);

        User user = new User();
        user.setID("9");
        user.setName("pinguino");
        user.setPassword("12345");
        userDao.add(user);

        User selectedUser = userDao.get("9");
        System.out.println(selectedUser.getID());
        System.out.println(selectedUser.getName());
        System.out.println(selectedUser.getPassword());
    }
}
