package com.asif;

import java.util.Scanner;

public class Binary {
   static int mid;

    public static void main(String[] args) {
        Scanner s1 = new Scanner(System.in);
        int s;

        System.out.println("enter length of array");
        int l = s1.nextInt();

        int[] a = new int[l];

        System.out.println("Enter the elements To array");
        for (int i = 0; i < l; i++) {
            a[i] = s1.nextInt();
        }

        System.out.println(" Array ");
        for (int i = 0; i < l; i++) {
            System.out.print(a[i] + "  ");
        }


        System.out.println("\n Ente the item To find");
        s= s1.nextInt();
        int lb=0,ub=l,f=0;

        while(lb<ub){
            mid=(lb+ub)/2;
            if(a[mid]==s){
                f=1;
                break;
            }
            else if(a[mid]<s){
                lb=mid+1;
            }
            else
                ub=mid;
        }

        if(f==1){
            System.out.println("Item Found  "+(mid+1)+ "  th position");
        }
        else
            System.out.println("item not found");


    }
}
