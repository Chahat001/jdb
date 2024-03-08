package db;

public class DataBase {
    private TransactionManager transactionManager;
    private MemTable memTable;

    private Object lock;


    public DataBase(){
        this.transactionManager = new TransactionManager();
        this.memTable = new MemTable();
        this.lock = new Object();
    }

    TransactionManager getTransactionManager(){
        return this.transactionManager;
    }

    String get(String key){
        return this.memTable.get(key);
    }

    void put(Entry entry){
        synchronized (this.lock){
            this.memTable.put(entry);
        }
    }
}
