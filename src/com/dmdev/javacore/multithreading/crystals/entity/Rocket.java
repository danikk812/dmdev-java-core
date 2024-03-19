package com.dmdev.javacore.multithreading.crystals.entity;

import java.util.Arrays;

public class Rocket {

    private final Crystal[] cargo;

    public Rocket(Crystal[] cargo) {
        this.cargo = cargo;
    }

    public Crystal[] getCargo() {
        return cargo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rocket rocket = (Rocket) o;
        return Arrays.equals(cargo, rocket.cargo);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(cargo);
    }

    @Override
    public String toString() {
        return "Rocket{" +
                "cargo=" + Arrays.toString(cargo) +
                '}';
    }
}
