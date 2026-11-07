package me.chanjar.weixin.common.util;

import java.security.SecureRandom;

public class RandomUtils {

  private static final String RANDOM_STR = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

  private static volatile SecureRandom random;

  private static SecureRandom getRandom() {
    if (random == null) {
      synchronized (RandomUtils.class) {
        if (random == null) {
          random = new SecureRandom();
        }
      }
    }
    return random;
  }

  public static String getRandomStr() {
    StringBuilder sb = new StringBuilder();
    SecureRandom r = getRandom();
    for (int i = 0; i < 16; i++) {
      sb.append(RANDOM_STR.charAt(r.nextInt(RANDOM_STR.length())));
    }
    return sb.toString();
  }

}
