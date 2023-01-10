package com.payhada.admin.common.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.concurrent.ThreadLocalRandom;

public class StringUtils {

    private StringUtils() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * generate random string (0-9A-Z)
     */
    public static String generateUpperCaseRandomString(int length) throws NoSuchAlgorithmException {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 90; // letter 'Z'
        SecureRandom random = SecureRandom.getInstanceStrong();

        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> i <= 57 || i >= 65)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public static String generateRandomNumberString() {
        int randomNumber = ThreadLocalRandom.current().nextInt(999999);
        return String.format("%06d", randomNumber);
    }
}
