package com.example.storage_sp.domain;


import java.util.Set;

public class Storage {
    public Storage(Set<Box> boxes) {
        this.boxes = boxes;
    }

    private final Set<Box> boxes;

    public Set<Box> getBoxes() {
        return boxes;
    }


}
