package com.asif;

public class Test {
    public static void printvalue(int i,int j,int k){
        System.out.println("int");
    }
    public static void printvalue(byte b){
        System.out.println("byte");
    }
    public static void main(String[] args){
        byte b=9;
        printvalue(b,b,b);
        String s1=args[1];
        String s2=args[2];
        System.out.println(s2);
    }

}
