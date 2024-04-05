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
        
## Bug Report:
    - Bug 1: Test 2 is failing new transcations are not able to read the latest key, 
             as they are searching for key+readTS, where readTS for new transaction will
             alwasy be greater. For ex t1 commits key as key 1, but t2 will look for
             key 2.

             Fix:
                add another search function which will fetch the latest key instead of the\
                exact match.



             Status: FIXED


 