/**
 * Created by govind.bhone on 11/22/2016.
 */


import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Day20 {

    public static void bubbleSort(int [] a){
        int noOfSwaps =0;
        int length =a.length;
        for(int i=0;i< length-1;i++){
            for(int j=0;j<length-1-i;j++){
                if(a[j]>a[j+1]){
                    noOfSwaps++;
                    int tmp=a[j];
                    a[j]=a[j+1];
                    a[j+1]=tmp;
                }
            }
        }
        System.out.println("Array is sorted in "+noOfSwaps+" swaps.");
        System.out.println("First Element: "+a[0]);
        System.out.println("Last Element: "+a[length-1]);
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int a[] = new int[n];
        for(int a_i=0; a_i < n; a_i++){
            a[a_i] = in.nextInt();
        }
        bubbleSort(a);
    }
}