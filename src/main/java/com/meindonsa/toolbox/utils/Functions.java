package com.meindonsa.toolbox.utils;

import com.meindonsa.toolbox.exception.FunctionalException;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class Functions {
    private Functions() {}

    private static final String BASE32_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ234567";

    public static String generateUniqueIdentifier(int size) {
        return UUID.randomUUID().toString().substring(0, size).replace("-", "").toLowerCase();
    }

    public static String generateBase32Secret(int length) {
        if (length <= 0) throw new IllegalArgumentException("length must be positive");
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(BASE32_CHARS.charAt(random.nextInt(BASE32_CHARS.length())));
        }
        return sb.toString();
    }

    public static String geneUniqueNumberIdentifier(int size) {
        Random random = new Random();
        long min = (long) Math.pow(10, size - 1);
        long max = (long) Math.pow(10, size) - 1;
        long randomNumber = min + random.nextLong(max - min + 1);
        return Long.toString(randomNumber);
    }

    public static URI createURI(final String uri) {
        try {
            return new URI(uri);
        } catch (URISyntaxException e) {
            throw new FunctionalException(e.toString());
        }
    }

    public static <T> List<T> getRemainingList(List<T> list, T element) {
        int index = list.indexOf(element);
        if (index == -1) {
            return null;
        }
        return list.subList(index, list.size());
    }

    public static String generateUpperCaseString(String name) {
        if (isNullOrEmpty(name)) throw new FunctionalException(ErrorMessages.ERR_INVALID_DATA);

        return name.toUpperCase().trim().replace(" ", "_");
    }

    public static String generateLowerCaweString(String name) {
        if (isNullOrEmpty(name)) throw new FunctionalException(ErrorMessages.ERR_INVALID_DATA);

        return name.toLowerCase().trim().replace(" ", "_");
    }

    public static boolean isNullOrEmpty(String string) {
        return string == null || string.trim().isEmpty();
    }

    public static boolean isNullOrEmpty(List<?> lists) {
        return lists == null || lists.isEmpty();
    }

    public static boolean isNullObject(Object obj) {
        return obj == null;
    }

    public static boolean isEndDateAfterStartDate(LocalDate startDate, LocalDate endDate) {
        return !endDate.isAfter(startDate);
    }

    public static boolean isEndDateAfterStartDate(LocalDateTime startDate, LocalDateTime endDate) {
        return !endDate.isAfter(startDate);
    }

    public static String handelKey(String key) {
        return Functions.isNullOrEmpty(key) ? null : key.trim().toLowerCase();
    }

    public static String handelUpperCaseKey(String key) {
        return Functions.isNullOrEmpty(key) ? null : key.trim().toUpperCase();
    }

    public static String handelLowerCaseKey(String key) {
        return Functions.isNullOrEmpty(key) ? null : key.trim().toLowerCase();
    }

    public static boolean hasInvalidString(String... strings) {
        if (strings == null) {
            return true;
        }
        for (String string : strings) {
            if (string == null || string.trim().isEmpty()) {
                return true;
            }
        }
        return false;
    }
}
