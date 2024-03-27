package com.missingsemester.hashmapapi.api.repository;

import com.missingsemester.hashmapapi.api.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;



@Repository
public class HashMapRepositoryImpl implements HashMapRepository {
    private final Map<Integer, User> database;

    public HashMapRepositoryImpl() {
        this.database = new HashMap<>();

        User user1 = new User(1, "ahmed");
        User user2 = new User(2, "Ghita");
        User user3 = new User(3, "ali");
        User user4 = new User(4, "Karim");
        User user5 = new User(5, "Ilham");
        User user6 = new User(6, "Salma");

        database.put(1, user1);
        database.put(2, user2);
        database.put(3, user3);
        database.put(4, user4);
        database.put(5, user5);
        database.put(6, user6);
    }

    @Override
    public User getValue(Integer key) {
        return database.get(key);
    }

    @Override
    public void setValue(Integer key, User value){
        database.put(key, value);
    }

    @Override
    public void deleteValue( Integer key){
        database.remove(key);
    }

    @Override
    public Integer getShardIndex(Integer key) {
        return null;
    }

    @Override
    public void moveValue(int source, int destination, Integer key, User value) {

    }

}
