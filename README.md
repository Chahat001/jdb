# Fast key-value database with MVCC transaction manager

## As of now Database have following capablities

  - Supports multi-threading
  - Support ACID properites
  - Support Mutli-version Concurrency Control based transaction manager, making it faster than open-source alernative such as redis

## To Do Version V1:

  - Query Processor
  - Add more capabilities to Stroage Engine:
     - Access Methods
     - Recovery Manager
     - Buffer Manager

## To Do Version V2:
  - Transport Layer:
      - Client Commmunciation
  - Execution Engine:
      - Local Execution

## To Do Version V3:
  - Transport Layer:
      - Cluster Commmunciation
  - Execution Engine:
      - Remote Execution
        
