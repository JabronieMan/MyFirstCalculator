package com.mattcrain;

public class Main {

    public static void main(String[] args) {
      StringNumber one = new StringNumber("127");
      StringNumber two = new StringNumber("1");

      one.add(two);

      System.out.println(one);
    }
}
