package com.example.springbootgradle.dao;

import com.example.springbootgradle.domain.User;

import java.sql.*;
import java.util.Map;

import static java.lang.System.getenv;

public abstract class UserDao {


    ConnectionMaker connectionMaker;
    //SimpleConnectionMaker connctionMaker = new SimpleConnectionMaker();

    public UserDao() {
        this.connectionMaker = new DConnectionMaker();
    }

    public void add(User user) throws ClassNotFoundException, SQLException {

        Connection conn = connectionMaker.makeConnection();

        PreparedStatement pstmt = conn.prepareStatement("insert into users(id, name, password) values(?, ?, ?)");
        pstmt.setString(1, user.getID());
        pstmt.setString(2, user.getName());
        pstmt.setString(3, user.getPassword());

        pstmt.executeUpdate();
        pstmt.close();
        conn.close();

    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        Connection conn = connectionMaker.makeConnection();

        PreparedStatement pstmt = conn.prepareStatement("select id, name, password from users where id = ?");
        pstmt.setString(1, id);
        ResultSet rs = pstmt.executeQuery();
        rs.next();

        User user = new User();
        user.setID(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));

        rs.close();
        pstmt.close();
        conn.close();

        return user;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserDao userDao = new NUserDao();
        User user = new User();
        user.setID("7");
        user.setName("durian");
        user.setPassword("12345");
        userDao.add(user);

        User selectedUser = userDao.get("7");
        System.out.println(selectedUser.getID());
        System.out.println(selectedUser.getName());
        System.out.println(selectedUser.getPassword());
    }

}
