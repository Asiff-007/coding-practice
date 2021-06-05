package com.asif;

import java.util.Scanner;

public class pira {
    public static void main(String args[]){
        Scanner s1=new Scanner(System.in);
        int a;
        do {
            System.out.print("Ente Number greater than 0  ");
            a = s1.nextInt();
            if(a<1)
                System.out.println("Number is less than 1");
        }while (a<1);
        for(int i=a;i>0;i--){
            for(int j=1;j<i+1;j++){
                System.out.print(j+" ");
            }
            System.out.println();
        }

    }
}
