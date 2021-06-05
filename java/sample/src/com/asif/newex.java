package com.asif;

public class newex {
    public static void main(String[] args){
        try{
            int a=10 , b=0;
            int c=a/b;
            System.out.println("result"+c);
        }
        catch (ArithmeticException e)
        {
            System.out.println("number cant devide by 0");
        }

            System.out.println("hello");

    }
}
