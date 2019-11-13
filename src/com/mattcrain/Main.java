package com.mattcrain;

public class Main {

    public static void main(String[] args) {
      StringNumber one = new StringNumber("123981723987123987");
      StringNumber two = new StringNumber("1987123987123978");

      one.add(two);

      System.out.println(one);
    }
}
