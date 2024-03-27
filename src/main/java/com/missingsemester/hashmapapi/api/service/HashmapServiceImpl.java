package com.missingsemester.hashmapapi.api.service;

import com.missingsemester.hashmapapi.api.model.User;
import com.missingsemester.hashmapapi.api.repository.HashMapRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service
public class HashmapServiceImpl implements ApiService {

    private final HashMapRepository hashmap;

    public HashmapServiceImpl(@Qualifier("hashMapRepositoryImpl") HashMapRepository hashmap) {
        this.hashmap = hashmap;
    }

    @Override
    public User getUser(int id){
        return hashmap.getValue(id);
    }

    @Override
    public void createUser(User user){
        int id = user.getId();
        hashmap.setValue(id, user);

    }
    @Override
    public void deleteUser(int id){
        hashmap.deleteValue(id);
    }
}
