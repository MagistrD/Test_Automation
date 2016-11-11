package util;

import java.util.Random;

public class RandomUtil {

    private static final int LENGTH = 3;

    public String randomString() {
        String alphabet = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890";
        Random random = new Random();
        int length = 0;
        while (length == 0) {
            length = random.nextInt(LENGTH);
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            stringBuilder.append(alphabet.charAt(random.nextInt(alphabet.length())));
        }
        return stringBuilder.toString();
    }
}
