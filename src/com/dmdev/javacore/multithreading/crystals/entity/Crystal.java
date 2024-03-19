package com.dmdev.javacore.multithreading.crystals.entity;

import com.dmdev.javacore.multithreading.crystals.entity.enums.CrystalColor;

import java.util.Objects;

public class Crystal {

    private final CrystalColor color;

    public Crystal(CrystalColor color) {
        this.color = color;
    }

    public CrystalColor getColor() {
        return color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Crystal crystal = (Crystal) o;
        return color == crystal.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color);
    }

    @Override
    public String toString() {
        return "Crystal{" +
                "color=" + color +
                '}';
    }
}
