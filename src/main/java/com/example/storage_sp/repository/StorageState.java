package com.example.storage_sp.repository;

import com.example.storage_sp.domain.Box;
import com.example.storage_sp.domain.Item;

import java.util.List;
import java.util.Set;

public interface StorageState {


    void saveBoxes(Set<Box> boxes);

    Set<Box> getAllBoxes();

    Set<Item> getAllItems();

    void removeItems(Set<String> itemsId);

    Set<Box> getFreeBoxes();
}
