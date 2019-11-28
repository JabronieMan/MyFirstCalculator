package com.mattcrain;

public class Main {

    public static void main(String[] args) {
      StringNumber one = new StringNumber("16383");
      StringNumber two = new StringNumber("16384");

      System.out.println(one.multiply(two).add(new StringNumber("16384")));
    }
}
