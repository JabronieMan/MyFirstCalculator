package com.mattcrain;

public class StringNumber {

  private final String binary;

  public StringNumber(String number) throws IllegalArgumentException {
    // Make sure it's actually a number
    if (!verifyNumber(number)) {
      throw new IllegalArgumentException("This is not a number: " + number);
    }
    binary = binaryString(number);
  }

  @Override
  public String toString() {
    return binary;
  }

  private static String binaryString(String decimalNum) {
    if (decimalNum.equals("0")) {
      return "0";
    }
    StringBuilder binary = new StringBuilder();
    while (!isZero(decimalNum)) {
      binary.append(oddAsOne(decimalNum));
      decimalNum = divDecimalStringByTwo(decimalNum);
    }
    return binary.reverse().toString();
  }

  private static String divDecimalStringByTwo(String decimalNum) {
    StringBuilder result = new StringBuilder();
    int carry = 0;

    char[] numbers = decimalNum.toCharArray();
    for (int i = 0; i < numbers.length; i++) {
      int digit = ((numbers[i] - '0') / 2) + carry;
      result.append(digit);
      carry = oddAsOne(numbers[i]) * 5;
    }

    return result.toString();
  }

  private static int oddAsOne(String decimalNum) {
    return oddAsOne(decimalNum.charAt(decimalNum.length()-1));
  }

  private static boolean isZero(String decimalNum) {
    for (int i = 0; i < decimalNum.length(); i++) {
      if (decimalNum.charAt(i) != '0') {
        return false;
      }
    }
    return true;
  }

  private static int oddAsOne(char num) {
    switch (num) {
      case '1':
      case '3':
      case '5':
      case '7':
      case '9':
        return 1;
    }
    return 0;
  }

  private static boolean verifyNumber(String number) {
    for (Character num : number.toCharArray()) {
      switch (num) {
        case '0':
        case '1':
        case '2':
        case '3':
        case '4':
        case '5':
        case '6':
        case '7':
        case '8':
        case '9':
          break;
        default:
          return false;
      }
    }
    return true;
  }
}
