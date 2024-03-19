package com.dmdev.javacore.multithreading.crystals;

import com.dmdev.javacore.multithreading.crystals.competition.Magician;
import com.dmdev.javacore.multithreading.crystals.entity.enums.MagicianType;
import com.dmdev.javacore.multithreading.crystals.storage.CrystalStorage;
import com.dmdev.javacore.multithreading.crystals.util.ThreadsUtil;

import java.util.concurrent.atomic.AtomicInteger;

public class ApplicationRunner {

    public static void main(String[] args) {
        CrystalStorage crystalStorage = new CrystalStorage();

        Magician fireMagician = new Magician(MagicianType.FIRE, crystalStorage);
        Magician airMagician = new Magician(MagicianType.AIR, crystalStorage);

        Thread fireThread = new Thread(fireMagician);
        Thread airThread = new Thread(airMagician);

        ThreadsUtil.startThreads(fireThread, airThread);
        ThreadsUtil.joinThreads(fireThread, airThread);
    }
}
