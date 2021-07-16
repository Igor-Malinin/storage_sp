package com.example.storage_sp.applications;

import com.example.storage_sp.domain.Box;
import com.example.storage_sp.domain.Item;
import com.example.storage_sp.repository.StorageState;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class StorageInterfaceImpl implements StorageInterface {

    public StorageInterfaceImpl(StorageState storageState) {
        this.storageState = storageState;
    }

    private final StorageState storageState;

    @Override
    public void createStorage(int cols, int rows, int boxCapacity) {
        Set<Box> boxes = new HashSet<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                boxes.add(new Box(new Box.BoxPosition(i, j), boxCapacity));
            }
        }
        storageState.saveBoxes(boxes);
    }


    @Override
    public boolean addItem(String type, String brandN, String description) {
        Box box = findAnyFreeBox();
        box.getItems().add(new Item(type, brandN, description));
        //storageState.saveBoxes(Collections.singleton(box));
        return true;
    }


    @Override
    public Set<Item> browseItemsByType(String itemType) {

        Set<Box> boxes = storageState.getAllBoxes();
        Set<Item> filteredItems = boxes.stream().flatMap(box -> box.getItems().stream())
                .filter(item -> item.getType().equals(itemType)).collect(Collectors.toSet());


        return filteredItems;

    }

    @Override
    public void takeItems(Set<String> itemsId) {
        Set<Item> allItems = storageState.getAllItems();
        //todo проверять, существуют ли такие идентификаторы
        itemsId.forEach(itemId -> {
            if (!checkExistentId(itemId, allItems)) {
                throw new RuntimeException("id " + itemId + " not found");
            }
        });
        storageState.removeItems(itemsId);

    }

    private boolean checkExistentId(String itemId, Set<Item> allItems) {
        return allItems.stream().anyMatch(item -> item.getId().equals(itemId));
    }


    private Box findAnyFreeBox() {
        Set<Box> freeBoxes = storageState.getFreeBoxes();

        return freeBoxes.stream().findAny().orElseThrow(() -> new RuntimeException("not enough free place"));

    }
}
