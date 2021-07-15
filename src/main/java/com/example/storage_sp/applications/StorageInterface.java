package com.example.storage_sp.applications;

import com.example.storage_sp.domain.Item;

import java.util.List;
import java.util.Set;

public interface StorageInterface {

  void   createStorage(int cols, int rows, int boxCapacity);

    boolean addItem(String type, String brandN, String description);

    Set<Item> browseItemsByType(String itemType);

    void takeItems(Set<String> itemsId);

}
