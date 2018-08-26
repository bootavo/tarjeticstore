package com.example.bucket.utils;

import java.util.UUID;

public class UUIDHelper {
    public static void main(String[] args) {
        System.out.println(generateString());
    }

    public static String generateString() {
        String uuid = UUID.randomUUID().toString();
        return uuid.substring(0,4);
    }
}