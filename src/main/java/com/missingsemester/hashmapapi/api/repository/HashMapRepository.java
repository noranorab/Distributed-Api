package com.missingsemester.hashmapapi.api.repository;

import com.missingsemester.hashmapapi.api.model.User;
import org.springframework.context.annotation.Bean;

public interface HashMapRepository {

    public User getValue(Integer key);
    public void setValue(Integer key, User value);
    public void deleteValue(Integer Key);
    public Integer getShardIndex(Integer key);
    public void moveValue(int source, int destination, Integer key, User value);
}
