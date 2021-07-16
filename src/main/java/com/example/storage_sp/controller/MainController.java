package com.example.storage_sp.controller;

import java.util.Set;

import com.example.storage_sp.applications.StorageInterface;
import com.example.storage_sp.applications.StorageInterfaceImpl;
import com.example.storage_sp.classforcontroller.ItemForController;
import com.example.storage_sp.domain.Box;
import com.example.storage_sp.domain.Item;
import com.example.storage_sp.repository.StorageState;
import com.example.storage_sp.repository.StorageStateImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("message")
public class MainController {
    private static final int STORAGE_COLS = 5;
    private static final int STORAGE_ROWS = 5;
    private static final int BOX_CAPACITY = 10;
    StorageState storageState;
    StorageInterface storage;

    public MainController() {
        storageState = new StorageStateImpl();
        storage = new StorageInterfaceImpl(storageState);
        storage.createStorage(STORAGE_COLS, STORAGE_ROWS, BOX_CAPACITY);
    }

    @GetMapping("/getAllItems")
    Set<Item> getAll(){
        return storageState.getAllItems();
    }

    @PostMapping("/addItem")
    boolean addItem(@RequestBody ItemForController item){
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

    /*@GetMapping("/showFreeBoxes")
    Set<Box> showFreeBoxes() {
        return storageState.getFreeBoxes();
    }*/


}
