package com.example.storage_sp.controller;

import java.util.concurrent.atomic.AtomicLong;

import com.example.storage_sp.applications.StorageInterface;
import com.example.storage_sp.applications.StorageInterfaceImpl;
import com.example.storage_sp.domain.Greeting;
import com.example.storage_sp.domain.Item;
import com.example.storage_sp.domain.Storage;
import com.example.storage_sp.repository.StorageState;
import com.example.storage_sp.repository.StorageStateImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Scanner;

@RestController
@RequestMapping("message")
public class MainController {
    private static final int STORAGE_COLS = 20;
    private static final int STORAGE_ROWS = 20;
    private static final int BOX_CAPACITY = 10;
    StorageState storageState;
    StorageInterface storage;

    public void initTest() {
        storageState = new StorageStateImpl();
        storage = new StorageInterfaceImpl(storageState);
        storage.createStorage(STORAGE_COLS, STORAGE_ROWS, BOX_CAPACITY);
        int MONITORS_COUNT = 25;
        int KEYBOARDS_COUNT = 20;
        // добавляем несколько предметов по типу.
        // место, на которое кладется предмет устанавливается автоматически (ближайшее свободное)
        for (int i = 0; i < KEYBOARDS_COUNT; i++) {
            storage.addItem("keyboard", "newKeyboard", "newDescription");
        }

        for (int i = 0; i < MONITORS_COUNT; i++) {
            storage.addItem("monitor", "newMonitor", "newDescription");
        }
    }

    @GetMapping("/getAllBoxes")
    public StorageStateImpl storageState(){
        return (StorageStateImpl) storageState.getAllItems();
    }
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }
}
