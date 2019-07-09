package com.mainacad.util;

import java.util.Random;
import java.util.logging.Logger;

public class Randomizer {
  private static Logger logger = Logger.getLogger(Randomizer.class.getName());

  public static int getRandomNumber(int min, int max) {
    int randowNumber = min + new Random().nextInt(max - min);
    //logger.info("Random number is " + randowNumber);

    return randowNumber;
  }

  public static String getRandomString(int length) {
    String alphabet = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
    char[] chars = alphabet.toCharArray();

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < length; i++) {
      sb.append(chars[getRandomNumber(0, chars.length - 1)]);
    }

    return sb.toString();
  }
}
