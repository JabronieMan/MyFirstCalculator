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
  void testAdd_expectNumbersAdd() {
    StringNumber one = new StringNumber("127");
    StringNumber two = new StringNumber("1");

    one.add(two);
    assertEquals(one.toString(), "10000000");
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

  @Test
  void testAdd_lotsOfNumbers() {
    for (int i = 0; i <= 100; i++) {
      for (int j = 0; j <= 100; j++) {
        StringNumber one = new StringNumber(String.valueOf(i));
        StringNumber two = new StringNumber(String.valueOf(j));

        one.add(two);
        assertEquals(one.toString(), Integer.toBinaryString(i + j));
      }
    }
  }

  @Test
  void testMultiply_lotsOfNumbers() {
    for (int i = 0; i <= 100; i++) {
      for (int j = 0; j <= 100; j++) {
        StringNumber one = new StringNumber(String.valueOf(i));
        StringNumber two = new StringNumber(String.valueOf(j));

        StringNumber result = one.multiply(two);
        assertEquals(Integer.toBinaryString(i * j), result.toString(), String.format("Trying %d * %d", i, j));
      }
    }
  }

  @Test
  void testCompareTo_expectWorks() {
    for (int i = 0; i <= 100; i++) {
      for (int j = 0; j <= 100; j++) {
        StringNumber one = new StringNumber(String.valueOf(i));
        StringNumber two = new StringNumber(String.valueOf(j));

        if (i > j) {
          assertTrue(one.compareTo(two) > 0);
        } else if (i < j) {
          assertTrue(one.compareTo(two) < 0);
        } else {
          assertEquals(0, one.compareTo(two));
        }
      }
    }
  }
}