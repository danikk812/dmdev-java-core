package com.dmdev.javacore.multithreading.crystals.util;

public final class ThreadsUtil {

    private ThreadsUtil() {
    }

    public static void startThreads(Runnable... runnables) {
        for (Runnable runnable : runnables) {
            new Thread(runnable).start();
        }
    }

    public static void joinThreads(Thread... threads) {
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
