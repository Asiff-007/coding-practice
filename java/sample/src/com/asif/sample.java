package com.asif;
import java.util.Scanner;


public class sample {
    public static void main(String[] args) {
        // write your code here
        Scanner s1=new Scanner(System.in);
        int i,amount=0;
        System.out.println("Enter invest met");
        i=s1.nextInt();
        for(int j=1;j<=30;j++ ){
            amount=amount+i;
            amount=amount+((amount*10)/100);



        }
        System.out.println("amount=  "+amount);

    }
}
