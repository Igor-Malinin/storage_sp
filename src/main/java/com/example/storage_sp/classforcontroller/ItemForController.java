package com.example.storage_sp.classforcontroller;

import java.util.UUID;

public class ItemForController {
    private  String type;
    private  String brandN;
    private  String description;
    private  String id;

    public ItemForController(String type, String brandN, String description) {
        this.type = type;
        this.brandN = brandN;
        this.description = description;
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getBrandN() {
        return brandN;
    }

    public String getDescription() {
        return description;
    }

}
