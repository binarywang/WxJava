package me.chanjar.weixin.common.util;

public class RandomUtils {

  private static final String RANDOM_STR = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

  private static volatile java.util.Random random;

  private static java.util.Random getRandom() {
    if (random == null) {
      synchronized (RandomUtils.class) {
        if (random == null) {
          random = new java.util.Random();
        }
      }
    }
    return random;
  }

  public static String getRandomStr() {
    StringBuilder sb = new StringBuilder();
    java.util.Random r = getRandom();
    for (int i = 0; i < 16; i++) {
      sb.append(RANDOM_STR.charAt(r.nextInt(RANDOM_STR.length())));
    }
    return sb.toString();
  }

}
