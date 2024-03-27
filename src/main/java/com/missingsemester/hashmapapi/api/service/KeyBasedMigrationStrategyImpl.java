package com.missingsemester.hashmapapi.api.service;

import com.missingsemester.hashmapapi.api.model.User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class KeyBasedMigrationStrategyImpl implements MigratingStrategy {

    private final ShardManager manager;

    public KeyBasedMigrationStrategyImpl(ShardManager manager) {
        this.manager = manager;
    }

    @Override
    public Boolean rebalance() {
        int numberOfShards = manager.getNumberOfShards();

        for (int destinationShard=numberOfShards-1; destinationShard>=0; destinationShard--){
            int sourceShard = (destinationShard -1) % numberOfShards;

            Map<Integer, User> sourceShardData = manager.getShardData(sourceShard);
            for (Map.Entry<Integer, User> entry : sourceShardData.entrySet()) {
                Integer key = entry.getKey();
                User user = entry.getValue();
                manager.migrateData(sourceShard, destinationShard, key, user);
            }
        }
        return true;

    }

    @Override
    public void handleFailure(Integer shardId) {

    }
}
