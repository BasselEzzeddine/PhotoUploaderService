package com.basselezzeddine.photouploader.utils;

import java.util.UUID;

public class RandomStringGenerator {

    public static String generate() {
        return UUID.randomUUID().toString();
    }
}
