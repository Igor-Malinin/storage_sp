package com.example.storage_sp.repository;

import com.example.storage_sp.domain.Box;
import com.example.storage_sp.domain.Item;
import com.example.storage_sp.domain.Storage;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class StorageStateImpl implements StorageState {

    private static Storage storage = new Storage(new HashSet<>());


    @Override
    public void saveBoxes(Set<Box> boxes) {
        storage.getBoxes().addAll(boxes);
    }

    @Override
    public Set<Box> getAllBoxes() {
        return new HashSet<>(storage.getBoxes());
    }

    @Override
    public Set<Box> getFreeBoxes() {
        return storage.getBoxes()
                .stream().filter(box -> box.getItems().size() < box.getMaxCapacity())
                .collect(Collectors.toSet());
    }

    @Override
    public void removeItems(Set<String> itemsId) {
        storage.getBoxes().forEach(box -> box.getItems().removeIf(item -> itemsId.contains(item.getId())));
    }

    @Override
    public Set<Item> getAllItems() {
        return storage.getBoxes().stream().flatMap(box -> box.getItems().stream()).collect(Collectors.toSet());
    }
}
