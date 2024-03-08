package db;

import java.sql.Timestamp;

public class Entry {
    String key;
    String value;

    Entry(String key, String value){
        this.key = key;
        this.value = value;
    }
}
