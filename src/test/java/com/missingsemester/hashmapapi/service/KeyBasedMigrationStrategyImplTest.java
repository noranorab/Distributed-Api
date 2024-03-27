package com.missingsemester.hashmapapi.service;

import com.missingsemester.hashmapapi.api.model.User;
import com.missingsemester.hashmapapi.api.service.KeyBasedMigrationStrategyImpl;
import com.missingsemester.hashmapapi.api.service.ShardManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class KeyBasedMigrationStrategyImplTest {
    @Mock
    private ShardManager manager;

    @InjectMocks
    private KeyBasedMigrationStrategyImpl strategy;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRebalance() {

        Map<Integer, User> sourceShardData = new HashMap<>();
        sourceShardData.put(1, new User(1, "User1"));
        sourceShardData.put(2, new User(2, "User2"));
        sourceShardData.put(3, new User(3, "User3"));

        when(manager.getNumberOfShards()).thenReturn(3); // Total number of shards
        when(manager.getShardData(anyInt())).thenReturn(sourceShardData); // Shard data for any shard


        when(manager.getNumberOfShards()).thenReturn(4); // Updated number of shards after adding one
        manager.addAShard();

        // Call the rebalance method
        strategy.rebalance();

        // Verify that migrateData is called for each key-value pair in the source shard
        verify(manager, times(12)).migrateData(anyInt(), anyInt(), anyInt(), any(User.class));
    }
}
