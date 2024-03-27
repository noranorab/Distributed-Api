package com.missingsemester.hashmapapi.api.service;

import com.missingsemester.hashmapapi.api.model.User;
import com.missingsemester.hashmapapi.api.repository.ShardedHashmapRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ShardManager {
    private final ShardedHashmapRepositoryImpl repository;


    private Integer numberOfShards;

    public ShardManager(ShardedHashmapRepositoryImpl repository, Integer numberOfShards){
        this.repository = repository;
        this.numberOfShards = numberOfShards;
    }

    public Boolean addAShard(){
        List<Map<Integer, User>> shards = this.repository.getShards();
        shards.add(new HashMap<>());
        numberOfShards++;
        return true;

    }
    public void removeAShard(){
        List<Map<Integer, User>> shards = this.repository.getShards();
        if (numberOfShards > 1) { // Ensure there's at least one shard
            shards.remove(numberOfShards - 1); // Remove the last shard
            numberOfShards--;
        }

    }
    public Integer getNumberOfShards() {
        return numberOfShards;
    }

    public Map<Integer, User> getShardData(int position){
        return repository.getShards().get(position);
    }

    public void migrateData(int source, int destination, Integer key, User value){
        repository.moveValue(source, destination, key, value);
    }






}
