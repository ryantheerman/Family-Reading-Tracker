package com.techelevator.dao;

import com.techelevator.model.User;

import java.util.List;

public interface UserDao {

    List<User> findAll();

    User getUserById(Long userId);

    User findByUsername(String username);

    int findIdByUsername(String username);

    boolean create(String username, String password, String role, boolean isParent);

    void updateFamilyId(int familyId, int currentUser);

    void addMember(String username, Long familyId);

}
