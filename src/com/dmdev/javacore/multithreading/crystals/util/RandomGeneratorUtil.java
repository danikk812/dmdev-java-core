package com.dmdev.javacore.multithreading.crystals.util;

import java.util.Random;

public final class RandomGeneratorUtil {

    private static final Random random = new Random();

    private RandomGeneratorUtil() {
    }

    public static int generateInt(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }

    public static boolean generateBoolean() {
        return random.nextBoolean();
    }
}
