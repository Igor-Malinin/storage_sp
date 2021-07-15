package com.example.storage_sp.controller;

import java.util.Set;

import com.example.storage_sp.applications.StorageInterface;
import com.example.storage_sp.applications.StorageInterfaceImpl;
import com.example.storage_sp.domain.Item;
import com.example.storage_sp.repository.StorageState;
import com.example.storage_sp.repository.StorageStateImpl;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("message")
public class MainController {
    private static final int STORAGE_COLS = 20;
    private static final int STORAGE_ROWS = 20;
    private static final int BOX_CAPACITY = 10;
    StorageState storageState;
    StorageInterface storage;

    public MainController() {
        storageState = new StorageStateImpl();
        storage = new StorageInterfaceImpl(storageState);
        storage.createStorage(STORAGE_COLS, STORAGE_ROWS, BOX_CAPACITY);
        int MONITORS_COUNT = 25;
        int KEYBOARDS_COUNT = 20;

        for (int i = 0; i < KEYBOARDS_COUNT; i++) {
            storage.addItem("keyboard", "newKeyboard", "newDescription");
        }

        for (int i = 0; i < MONITORS_COUNT; i++) {
            storage.addItem("monitor", "newMonitor", "newDescription");
        }
        Set<String> monitorIds = storage.browseItemsByType("monitor").stream().map(Item::getId).collect(Collectors.toSet());
        Set<String> keyboardsIds = storage.browseItemsByType("keyboard").stream().map(Item::getId).collect(Collectors.toSet());
    }

    @GetMapping("/getAllItems")
    Set<Item> getAll(){
        return storageState.getAllItems();
    }
    @PostMapping("/addItem")
    boolean addItem(@RequestBody Item item){
        return storage.addItem(item.getType(), item.getBrandN(), item.getDescription());
    }
    @GetMapping("/showItemsByType/{type}")
    Set<Item> showItemsByType(@PathVariable String type){
        return storage.browseItemsByType(type);
    }
    @GetMapping("/deleteItemsByID/{id}")
    void takeItemFromStorage(@PathVariable Set<String> id){
        storage.takeItems(id);
    }


}
