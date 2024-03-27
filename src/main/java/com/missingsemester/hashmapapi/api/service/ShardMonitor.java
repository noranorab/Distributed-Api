package com.missingsemester.hashmapapi.api.service;

import com.missingsemester.hashmapapi.api.repository.ShardedHashmapRepositoryImpl;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class ShardMonitor {
    private final ShardManager manager;
    private final ShardedHashmapRepositoryImpl repository;
    private final MigratingStrategy strategy;

    public ShardMonitor(ShardManager manager, ShardedHashmapRepositoryImpl repository, MigratingStrategy strategy) {
        this.manager = manager;
        this.repository = repository;
        this.strategy = strategy;
    }

    @Scheduled(fixedDelay = 10000)
    public void monitorShards() {
        int maxRecordsPerShard = 3;
        boolean shouldRebalance = false;
        for (int shardIndex = 0; shardIndex < repository.getNumOfShards(); shardIndex++) {
            int recordsInShard = repository.getShards().get(shardIndex).size();
            if (recordsInShard > maxRecordsPerShard) {
                shouldRebalance = true;
                System.out.println("Shards are healthy...");
                break; // Exit the loop if any shard exceeds the maximum records
            }
        }

        if (shouldRebalance) {
            manager.addAShard();
            strategy.rebalance();
            System.out.println("Added a new shard and rebalanced the data.");
        }
    }
}
