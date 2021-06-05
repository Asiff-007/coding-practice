package com.asif;
import java.util.Scanner;
public class Bubble {
    public static void main(String[] args) {
        Scanner s1=new Scanner(System.in);
        int temp;

        System.out.println("enter length of array");
        int l=s1.nextInt();

        int[] a=new int[l];

        System.out.println("Enter the elements To array");
        for (int i=0;i<l;i++){
            a[i]=s1.nextInt();
        }

        System.out.println("array");
        for (int i=0;i<l;i++){
            System.out.print(a[i]+"  ");
        }

        for(int i=0;i<l;i++){
            for(int j=0;j<l-i-1;j++){
                if(a[j]>a[j+1]){
                    temp=a[j];
                    a[j]=a[j+1];
                    a[j+1]=temp;
                }
            }
        }
        System.out.println();

        System.out.println("array after sort");
        for (int i=0;i<l;i++){
            System.out.print(a[i]+"  ");
        }

    }
}
