package com.dmdev.javacore.multithreading.crystals.competition;

import com.dmdev.javacore.multithreading.crystals.entity.Crystal;
import com.dmdev.javacore.multithreading.crystals.entity.Rocket;
import com.dmdev.javacore.multithreading.crystals.entity.enums.CrystalColor;
import com.dmdev.javacore.multithreading.crystals.entity.enums.MagicianType;
import com.dmdev.javacore.multithreading.crystals.exception.MagicianInterruptedException;
import com.dmdev.javacore.multithreading.crystals.storage.CrystalStorage;
import com.dmdev.javacore.multithreading.crystals.util.RandomGeneratorUtil;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static com.dmdev.javacore.multithreading.crystals.storage.CrystalStorage.REQUIRED_CRYSTALS;

public class Magician implements Runnable {

    private final MagicianType type;
    private final CrystalStorage crystalStorage;

    public Magician(MagicianType type, CrystalStorage crystalStorage) {
        this.type = type;
        this.crystalStorage = crystalStorage;
    }

    @Override
    public void run() {
        while (!crystalStorage.isCompetitionOver()) {
            Rocket rocket = fetchCrystals();
            unloadCrystals(rocket);
            try {
                Thread.sleep(1000); // Travel time simulate
            } catch (InterruptedException e) {
                throw new MagicianInterruptedException(this.type + " magician was interrupted while space rocket flight");
            }
        }
        synchronized (System.out) {
            logCompetitionEnd();
        }
    }

    private Rocket fetchCrystals() {
        int cargoSize = RandomGeneratorUtil.generateInt(CrystalStorage.MIN_CRYSTALS_PER_DAY, CrystalStorage.MAX_CRYSTALS_PER_DAY);
        Crystal[] cargo = new Crystal[cargoSize];
        for (int i = 0; i < cargoSize; i++) {
            cargo[i] = generateRandomCrystal();
        }
        return new Rocket(cargo);
    }

    private void unloadCrystals(Rocket rocket) {
        Crystal[] cargo = rocket.getCargo();
        int redCrystals = 0;
        int whiteCrystals = 0;
        for (Crystal crystal : cargo) {
            if (crystal.getColor() == CrystalColor.RED) {
                redCrystals++;
            } else {
                whiteCrystals++;
            }
            crystalStorage.addCrystal(crystal, type);
        }
        logProgress(redCrystals, whiteCrystals);
    }

    private Crystal generateRandomCrystal() {
        return new Crystal(RandomGeneratorUtil.generateBoolean() ? CrystalColor.RED : CrystalColor.WHITE);
    }

    private void logProgress(int redCrystals, int whiteCrystals) {
        System.out.println("Magician " + type + " has collected: Red crystals: " + redCrystals + ", White crystals: " + whiteCrystals);
    }

    private void logCompetitionEnd() {
        System.out.println("Competition is over for Magician " + type + "!");
        System.out.println("Final count:");
        for (Map.Entry<MagicianType, AtomicInteger[]> entry : crystalStorage.getCrystalsCollected().entrySet()) {
            MagicianType magicianType = entry.getKey();
            AtomicInteger[] counts = entry.getValue();
            System.out.println(magicianType + " crystals: Red=" + counts[CrystalColor.RED.ordinal()].get() + ", White=" + counts[CrystalColor.WHITE.ordinal()].get());
        }
    }

}