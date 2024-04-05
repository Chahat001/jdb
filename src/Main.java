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
        txn2.write("key1", "value2");
        txn2.commit();

        Transaction txn3 = new Transaction(inMemoryDB);
        txn3.write("key1", "value3");
        txn3.commit();

        Transaction txn4 = new Transaction(inMemoryDB);
        txn4.write("key1", "value4");
        txn4.commit();

        Transaction txn5 = new Transaction(inMemoryDB);
        assert txn5.read("key1").equals("value4");
        txn5.commit();

        // Test 3: Since isolation level is Serializable. Same Transaction should read only its data
        assert txn3.read("key1").equals("value3");


    }
}