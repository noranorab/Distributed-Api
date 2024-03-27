package com.missingsemester.hashmapapi.api.service;

import com.missingsemester.hashmapapi.api.model.User;
import com.missingsemester.hashmapapi.api.repository.HashMapRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ShardingServiceImpl implements ApiService {

    private final HashMapRepository repository;

    public ShardingServiceImpl(@Qualifier("shardedHashmapRepositoryImpl") HashMapRepository repository) {
        this.repository = repository;
    }

    @Override
    public User getUser(int id) {
        return repository.getValue(id);
    }

    @Override
    public void createUser(User user) {
        int id = user.getId();
        repository.setValue(id, user);

    }

    @Override
    public void deleteUser(int id) {
        repository.deleteValue(id);

    }
}
