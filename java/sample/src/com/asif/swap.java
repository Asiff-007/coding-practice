package com.asif;

import java.sql.SQLOutput;

public class swap {
    public static void main(String args[]){
        int a=5;
        int b=6;
        System.out.println("before swap \n A = "+a+ " \n B = "+b);
        a=a+b;
        b=a-b;
        a=a-b;
        System.out.println("before swap \n A = "+a+ " \n B = "+b);

    }

}
