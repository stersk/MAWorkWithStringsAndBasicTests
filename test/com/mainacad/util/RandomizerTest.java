package com.mainacad.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class RandomizerTest {

  @Test
  void testGetRandomNumber() {
    Integer randomNumber = Randomizer.getRandomNumber(0, 100);
    assertNotNull(randomNumber);
    assertTrue(randomNumber >= 0 && randomNumber <= 100);
  }

  @Test
  void testGetRandomString() {
    String testString = Randomizer.getRandomString(20);
    assertNotNull(testString);
    assertEquals(testString.length(), 20);
  }
}