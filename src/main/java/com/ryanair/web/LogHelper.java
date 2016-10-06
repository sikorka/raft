package com.ryanair.web;

/**
 * Dummy logging.
 */
public class LogHelper {

    /** Displayes msg in <code>System.out</code>. */
    public static void report(String msg) {
        System.out.println(msg);
    }

    public static void success(String s) {
        report("GREAT: " + s);
    }

    public static void fail(String s)  {
        report("TOO BAD: " + s);
    }
}
