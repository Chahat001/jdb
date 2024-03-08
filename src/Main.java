import db.DataBase;
import db.Transaction;

import java.awt.desktop.SystemEventListener;

public class Main {
    public static void main(String[] args) {
        DataBase inMemoryDB = new DataBase();
        Transaction txn1 = new Transaction(inMemoryDB);

        txn1.write("key1", "value1");
        txn1.commit();

        System.out.print(txn1.read("key1"));
    }
}