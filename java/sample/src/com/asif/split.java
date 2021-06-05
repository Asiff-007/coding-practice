package com.asif;
import java.util.Scanner;

public class split {
    public static void main(String[] args){
        Scanner s=new Scanner(System.in);
        String str,rstr=" ";
        String[] str1;
        System.out.println("Enter The String  :  ");
        str=s.nextLine();
        System.out.println("String is   "+str);
        str1=str.split(" ");
       // StringBuffer b=new StringBuffer();
        for(int i=str1.length-1;i>=0;i--){
           rstr=str1[i];
            for(int j=rstr.length()-1;j>=0;j--){


            }

        }

    }
}
