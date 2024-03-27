package com.missingsemester.hashmapapi.api.repository;

import com.missingsemester.hashmapapi.api.model.User;
import com.missingsemester.hashmapapi.api.service.ShardMonitor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ShardedHashmapRepositoryImpl implements HashMapRepository {

    private final List<Map<Integer, User>> shards;

    private final Integer numOfShards;
    public Integer getNumOfShards() {
        return numOfShards;
    }

    public ShardedHashmapRepositoryImpl(Integer numOfShards) {
        this.shards = new ArrayList<>();
        this.numOfShards = numOfShards;
        for (int i=0; i<numOfShards; ++i){
            shards.add(new HashMap<>());
        }

    }
    public List<Map<Integer, User>> getShards() {
        return shards;
    }

    @Override
    public User getValue(Integer key) {
        Integer index = getShardIndex(key);
        return shards.get(index).get(key);
    }

    @Override
    public void setValue(Integer key, User value) {
        Integer index = getShardIndex(key);
        shards.get(index).put(key, value);

    }

    @Override
    public void deleteValue(Integer key) {
        Integer index = getShardIndex(key);
        shards.get(index).remove(key);

    }

    @Override
    public Integer getShardIndex(Integer key) {
        return Math.abs(key) % numOfShards;
    }

    @Override
    public void moveValue(int source, int destination, Integer key, User value){
        Map<Integer, User> sourceShard = this.shards.get(source);
        Map<Integer, User> destinationShard = this.shards.get(destination);

        // Remove the key-value pair from the source shard
        User removedValue = sourceShard.remove(key);

        // Add the key-value pair to the destination shard
        destinationShard.put(key, removedValue);



    }
}
