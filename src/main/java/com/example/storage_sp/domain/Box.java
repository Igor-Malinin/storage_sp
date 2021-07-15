package com.example.storage_sp.domain;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public class Box {

    public Box(BoxPosition position, int maxCapacity) {
        this.maxCapacity = maxCapacity;
        this.items = new HashSet<>();
        this.position = position;
        this.id = UUID.randomUUID().toString();
    }


    private final Set<Item> items;
    private final BoxPosition position;
    private final int maxCapacity;
    private final String id;

    public Set<Item> getItems() {
        return items;
    }

    public BoxPosition getPosition() {
        return position;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Box box = (Box) o;
        return Objects.equals(id, box.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public static class BoxPosition {
        public BoxPosition(int col, int row) {
            this.col = col;
            this.row = row;
        }

        int col;
        int row;
    }

}
