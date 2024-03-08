package db;

import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;


enum TransactionState {
    PENDING,
    COMMITTED,
    ABORTED,
    NOOP
}
public class Transaction {
    private DataBase db;
    private HashMap<String, String> writeSet;
    private HashSet<String>  readSet;
    private String txnId;
    private int readTS;
    private TransactionState state;

    public Transaction(DataBase db){
        this.db = db;
        this.writeSet = new HashMap<>();
        this.readSet = new HashSet<>();
        this.txnId = UUID.randomUUID().toString();
        this.readTS = db.getTransactionManager().getCurrentTimeStamp();
    }

    public String read(String key){
        /**
         * if this transaction has any writes for this key, fulfill from there.
         *         else, load latest version from its snapshot of the db and track the
         *         read key
         */
        if (this.writeSet.containsKey(key)){
            return this.writeSet.get(key);
        }

        this.readSet.add(key);

        String keyWithTS =  key + " " + this.readTS; // key+1 => key1
        String value = db.get(keyWithTS);

        return value;
    }

    public void write(String key, String value){
        writeSet.put(key, value);
    }

    TransactionState getCurrentState(){
        return this.state;
    }

    HashSet getReadSet(){
        return this.readSet;
    }

    int getReadTS(){
        return this.readTS;
    }

    HashMap getWriteSet(){
        return this.writeSet;
    }

    public void commit(){

        // if nothing to commit change transcation status to NOOP
        if(this.writeSet.size() == 0){
            this.state = TransactionState.NOOP;
        }

        try{
            this.db.getTransactionManager().commit_request(this);
        }
        catch (TransactionExceptions e){
            System.out.println("Transcation : " + this.txnId + "Aborted." + "Error :" + e.getMessage());
            this.state = TransactionState.ABORTED;
            return;
        }

        for(String key : this.writeSet.keySet()){
            Entry entry = new Entry(key + " " + this.readTS, this.writeSet.get(key));
            this.writeSet.remove(key);
            this.db.put(entry);
        }
        this.state = TransactionState.COMMITTED;
    }

}
