package util;

public class LogPrinter {
    public static void logMsg(String msg) {
        System.out.println(String.format("%d|%s|%s", System.currentTimeMillis(), Thread.currentThread().getName(), msg));
    }
}
