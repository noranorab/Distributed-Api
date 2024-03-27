package com.missingsemester.hashmapapi.api.service;

public interface MigratingStrategy {

    public Boolean rebalance();
    public void handleFailure(Integer shardId);
}
