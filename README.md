# Distributed-Api
This is a simple sharded api written in Spring Boot

## Project Overview
This project aims to :
* Create a straightforward HTTP API emulating the functionality of a HashMap.
* Shard the data written to the HashMap using Hashed Sharding scheme. 

## Dependencies
This project needs the following dependencies:

* IntelliJ IDEA 2023.2.5
* JDK 17
* Postman

## Basic Build and Run Instructions
1. Clone this repository and Open with IntelliJ IDE.
2. Run the application.
3. Open Postman and test API using this link:
   https://sosteto.postman.co/workspace/My-Workspace~af131ae5-4df1-4214-8727-9bec39048774/collection/24397362-f602f128-f14f-40a6-ab44-2d8349fa7096?action=share&creator=24397362
5. Run the tests

## Description

* The Sharding API project aims to implement a scalable and efficient data storage system using the hash sharding scheme. This scheme partitions data across multiple shards based on a hash function, where the hash value determines the shard index. Each shard operates as an independent data store, allowing for horizontal scalability and improved performance by distributing the workload.

### Class Descriptions:

<b>User Model (User.java):</b>
* Represents a user entity with attributes such as user ID and name.

<b>ShardedHashmapRepositoryImpl (ShardedHashmapRepositoryImpl.java):</b>
* It Acts as the repository responsible for storing user data across multiple shards (the number of shards set for this project can be changed in AppConfig.java).
* Implements the HashMapRepository interface to provide CRUD (Create, Read, Update, Delete) operations.
* Utilizes the hash sharding scheme to distribute user data among shards. The hash value is obtained with the simple operation Math.abs(key) % numOfShards
* Maintains a list of shard maps, where each map stores user data for a specific shard.
* Implements methods to retrieve the shard index for a given user ID, get, set, and delete user data from the appropriate shard, and move data between shards when scaling.
  
<b>ShardManager (ShardManager.java):</b>
* Manages the creation and deletion of shards in the system.
* Provides methods to add a new shard dynamically (rebalancing records accross shards).
* Ensures proper distribution of data across shards and maintains shard consistency (A monitor runs every 10s to ensure that size of each shards doesn't exceed 3 records, it triggers the rebalancing if that's the case).
  
<b>MigratingStrategy (MigratingStrategy.java):</b>

* Defines the strategy for migrating data between shards to maintain load balancing and optimize system performance.
* Implements algorithms for rebalancing data distribution when shards become overloaded or underutilized.
  
<b>ShardMonitor (ShardMonitor.java):</b>

* Monitors the status of shards periodically to detect changes in data distribution or shard capacity.
* Utilizes scheduled tasks (e.g., Spring's @Scheduled annotation) to trigger monitoring at regular intervals.
* Implements logic to check if any shard exceeds the maximum allowed records and initiates rebalancing actions if necessary.
  
<b>UserController (UserController.java):</b>
* Provides RESTful endpoints for interacting with user data.
* Defines methods to handle HTTP requests for creating, retrieving, and deleting user records.
* Communicates with the ShardingServiceImpl to perform CRUD operations on user data.

<b>HashmapApiApplication (HashmapApiApplication.java):</b>

* Main entry point of the application.
* Bootstraps the Spring Boot application and configures necessary components.
* Uses annotations like @SpringBootApplication and @Import to set up the application context and import additional configuration.

## Result
Here some screen shots:
<img src="Desktop\testCase.png"/>
<img src="Desktop\runningapp.png"/>



