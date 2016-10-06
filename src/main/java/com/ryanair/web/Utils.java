package com.ryanair.web;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * Eclectic set of useful methods.
 */
public class Utils {

    public static String threeLetters() {
        return RandomStringUtils.randomAlphabetic(3).toLowerCase();
    }
}
