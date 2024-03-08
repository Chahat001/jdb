package db;

import java.util.ArrayList;

public class MemTable {
    private AVLTree indexer;
    private ArrayList<Entry> dataStorage;

    MemTable(){
        this.indexer  = new AVLTree();
        this.dataStorage = new ArrayList<>();

    }

    String get(String key){
        AVLNode indexNode = this.indexer.search(key);

        if(indexNode == null){
            return null;
        }

        int entryLocationIndex = indexNode.entryLocationIndex;
        Entry entry = this.dataStorage.get(entryLocationIndex);

        return entry.value;
    }

    void put(Entry entry){
        this.dataStorage.add(entry);
        int locationIndex = dataStorage.size()-1;

        this.indexer.insert(entry.key, locationIndex);
    }
}
