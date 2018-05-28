package com.videocean.service.dao;

import com.videocean.exception.UserException;
import com.videocean.model.User;

import java.util.List;

public interface IUserDAO {

    int addUser(User user) throws UserException;

    void updateUser(User user) throws UserException;

    void removeUser(int userID) throws UserException;

    User getUserById(int userId) throws UserException;

    User getUserByEmailAndPass(String email, String password) throws UserException;

    List<User> getUsersByName(String name) throws UserException;

}