package com.mattcrain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StringNumberTest {

  @Test
  void testToString_expectBinaryRepresentation() {
    StringNumber test = new StringNumber("12345");
    assertEquals(test.toString(), "11000000111001");
  }

  @Test
  void testNotNumber_expectException() {
    assertThrows(IllegalArgumentException.class, () -> new StringNumber("not number"));
  }

  @Test
  void testToString_lotsOfNumbers() {
    for (int i = 0; i <= 1024; i++) {
      StringNumber test = new StringNumber(String.valueOf(i));
      assertEquals(test.toString(), Integer.toBinaryString(i));
    }
  }
}