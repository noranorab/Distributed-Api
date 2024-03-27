package com.missingsemester.hashmapapi.api.service;

import com.missingsemester.hashmapapi.api.model.User;

public interface ApiService {

    public User getUser(int id);

    public void createUser(User user);

    public void deleteUser(int id);
}
