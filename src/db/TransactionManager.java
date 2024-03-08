package db;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;


public class TransactionManager {
    private int nextTS;
    private ConcurrentHashMap<String, Integer> keyToLastCommitTS;

    private Object lock;

    TransactionManager(){
        this.nextTS = 1;
        this.keyToLastCommitTS = new ConcurrentHashMap<>();
        this.lock = new Object();
    }

    int getCurrentTimeStamp(){
        /**
          if a transaction gets a commit timestamp of 1
        then its snapshot of the db includes everything that occurred until 0
         **/
        return this.nextTS-1;
    }

    void commit_request(Transaction txn) throws TransactionExceptions{

        /*
        This code need to be thread safe as multiple transaction would be accessing it
         */
        synchronized (this.lock) {
            HashSet<String> transReadSet = txn.getReadSet();
            for (String key : transReadSet) {
                int lastCommit = this.keyToLastCommitTS.getOrDefault(key, -1);
                if (lastCommit > txn.getReadTS()) {
                    throw new TransactionExceptions("Read set has been changed");
                }
            }

            int currentTS = this.nextTS;
            this.nextTS++;

            Set<String> keysToBeCommited = txn.getWriteSet().keySet();
            for(String key : keysToBeCommited){
                this.keyToLastCommitTS.put(key, currentTS);
            }
        }
    }
}
