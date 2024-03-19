package com.dmdev.javacore.multithreading.crystals.storage;

import com.dmdev.javacore.multithreading.crystals.entity.Crystal;
import com.dmdev.javacore.multithreading.crystals.entity.enums.CrystalColor;
import com.dmdev.javacore.multithreading.crystals.entity.enums.MagicianType;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class CrystalStorage {

    public static final int REQUIRED_CRYSTALS = 20;
    private static final int INITIAL_CRYSTALS = 0;
    public static final int MIN_CRYSTALS_PER_DAY = 2;
    public static final int MAX_CRYSTALS_PER_DAY = 5;
    private final Map<MagicianType, AtomicInteger[]> crystalsCollected = new HashMap<>();

    private final static int RED_CRYSTAL_INDEX = 0;
    private final static int WHITE_CRYSTAL_INDEX = 1;

    public CrystalStorage() {
        crystalsCollected.put(MagicianType.FIRE, new AtomicInteger[]{new AtomicInteger(INITIAL_CRYSTALS), new AtomicInteger(INITIAL_CRYSTALS)});
        crystalsCollected.put(MagicianType.AIR, new AtomicInteger[]{new AtomicInteger(INITIAL_CRYSTALS), new AtomicInteger(INITIAL_CRYSTALS)});
    }

    public Map<MagicianType, AtomicInteger[]> getCrystalsCollected() {
        return crystalsCollected;
    }

    public synchronized boolean addCrystal(Crystal crystal, MagicianType type) {
        if (isCompetitionOver()) {
            return false;
        }

        AtomicInteger[] counts = crystalsCollected.get(type);
        if (counts[RED_CRYSTAL_INDEX].get() >= REQUIRED_CRYSTALS && counts[WHITE_CRYSTAL_INDEX].get() >= REQUIRED_CRYSTALS) {
            return false; // The team already won
        }

        int colorIndex = (crystal.getColor() == CrystalColor.RED) ? RED_CRYSTAL_INDEX : WHITE_CRYSTAL_INDEX;
        counts[colorIndex].incrementAndGet();
        return true;
    }


    public synchronized boolean isCompetitionOver() {
        for (AtomicInteger[] counts : crystalsCollected.values()) {
            if (counts[RED_CRYSTAL_INDEX].get() >= REQUIRED_CRYSTALS && counts[WHITE_CRYSTAL_INDEX].get() >= REQUIRED_CRYSTALS) {
                return true;
            }
        }
        return false;
    }
}
