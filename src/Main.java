import db.DataBase;
import db.Transaction;

import java.awt.desktop.SystemEventListener;

public class Main {
    public static void main(String[] args) {
        DataBase inMemoryDB = new DataBase();

        // Test 1: Transaction should read the same value it wrote - Serializability
        Transaction txn1 = new Transaction(inMemoryDB);

        txn1.write("key1", "value1");
        txn1.commit();

        assert txn1.read("key1").equals("value1");

        // Test 2 : New Transcation should read the latest write
        Transaction txn2 = new Transaction(inMemoryDB);
        assert txn2.read("key1").equals("value1");
        txn2.commit();
    }
}