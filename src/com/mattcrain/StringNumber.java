package com.mattcrain;

public class StringNumber implements Comparable<StringNumber> {

  private String binary;

  public StringNumber(String number) throws IllegalArgumentException {
    // Make sure it's actually a number
    if (!verifyNumber(number)) {
      throw new IllegalArgumentException("This is not a number: " + number);
    }
    binary = binaryString(number);
  }

  public StringNumber add(StringNumber other) {
    String one = binary;
    String two = other.binary;

    // Normalize lengths by padding zeros to the shorter number
    int maxLen = Math.max(one.length(), two.length());
    one = padZeros(one, maxLen);
    two = padZeros(two, maxLen);

    StringBuilder result = new StringBuilder();
    char carry = '0';
    for (int i = one.length() -1; i >= 0; i--) {
      AddResult add = new AddResult(one.charAt(i), two.charAt(i), carry);
      result.append(add.result);
      carry = add.carry;
    }
    if (carry == '1') {
      result.append(carry);
    }

    binary = result.reverse().toString();

    return this;
  }

  public StringNumber multiply(StringNumber other) {
    String one = binary;
    String two = other.binary;

    if (one.length() < two.length()) {
      String tmp = one;
      one = two;
      two = tmp;
    }

    StringNumber result = new StringNumber("0");
    for (int i = two.length() - 1; i >= 0; i--) {
      if (two.charAt(i) == '1') {
        StringNumber tmp = new StringNumber("0");
        tmp.binary = one;
        result.add(tmp);
      }
      one = one + "0";
    }
    return result;
  }

  @Override
  public String toString() {
    return binary;
  }

  @Override
  public int compareTo(StringNumber other) {
    if (binary.length() > other.binary.length()) {
      return 1;
    } else if (binary.length() < other.binary.length()) {
      return -1;
    }
    // The numbers have the same binary length.
    for (int i = 0; i < binary.length(); i++) {
      if (binary.charAt(i) == other.binary.charAt(i)) {
        continue;
      }
      if (binary.charAt(i) == '1') {
        return 1;
      } else {
        return -1;
      }
    }
    return 0;
  }

  private String padZeros(String str, int len) {
    if (str.length() == len) {
      return str;
    }
    return String.format("%1$" + len + "s", str).replace(' ', '0');
  }

  // Converts a string based decimal number to a string based binary number (e.g. "32" -> "100000")
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

  private class AddResult {
    final char result;
    final char carry;

    AddResult(char one, char two, char carry) {
      char[] nums = new char[]{one, two, carry};
      int ones = 0;
      for (int i = 0; i < nums.length; i++) {
        if (nums[i] == '1') {
          ones++;
        }
      }
      result = (ones == 2 || ones == 0) ? '0' : '1';
      this.carry = (ones >= 2) ? '1' : '0';
    }
  }
}
