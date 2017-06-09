package lancer.utils;


public class Time {

    /**
     * Расчитываем время в нано секундах
     */
    public static final long SECOND = 1000000000L;

    public static long get() {
        return System.nanoTime();
    }

}
