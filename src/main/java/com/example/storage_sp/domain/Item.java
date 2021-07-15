package com.example.storage_sp.domain;

import java.util.*;

public class Item {

    private final String type;
    private final String brandN;
    private final String description;
    private final String id;

    public Item(String type, String brandN, String description) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return id.equals(item.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
