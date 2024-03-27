package com.missingsemester.hashmapapi.service;

import com.missingsemester.hashmapapi.api.model.User;
import com.missingsemester.hashmapapi.api.repository.ShardedHashmapRepositoryImpl;
import com.missingsemester.hashmapapi.api.service.MigratingStrategy;
import com.missingsemester.hashmapapi.api.service.ShardManager;
import com.missingsemester.hashmapapi.api.service.ShardMonitor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class ShardMonitorTest {
    @Mock
    private ShardManager manager;
    @Mock
    private ShardedHashmapRepositoryImpl repository;
    @Mock
    private MigratingStrategy strategy;
    @InjectMocks
    private ShardMonitor monitor;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testMonitorShards_ShouldRebalanceData_WhenShardExceedsMaxRecords(){

        int maxRecordsPerShard = 3;
        int shardIndex = 0;
        int recordsInShard = maxRecordsPerShard + 1;

        List<Map<Integer, User>> shards = new ArrayList<>();
        Map<Integer, User> shardData = new HashMap<>();
        shardData.put(1, new User(1, "User1"));
        shardData.put(2, new User(2, "User2"));
        shardData.put(3, new User(3, "User3"));
        shardData.put(4, new User(4, "User4"));
        shards.add(shardData);

        when(repository.getNumOfShards()).thenReturn(1);
        when(repository.getShards()).thenReturn(shards);
        when(repository.getShardIndex(anyInt())).thenReturn(shardIndex);
        when(manager.addAShard()).thenReturn(true);
        when(strategy.rebalance()).thenReturn(true);

        // Call the method under test
        monitor.monitorShards();

        verify(manager, times(1)).addAShard();
        verify(strategy, times(1)).rebalance();



    }



}
