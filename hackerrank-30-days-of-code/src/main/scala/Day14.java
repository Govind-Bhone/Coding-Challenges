/**
 * Created by govind.bhone on 11/10/2016.
 */

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;


class Difference {
    private int[] elements;
    public int maximumDifference;

    // Add your code here

    public Difference(int[] elements) {
        this.elements = elements;
    }

    public void computeDifference() {
        int maxNumber = 0;
        int minNumber = 100;

        for (int ele : elements) {
            if (ele < minNumber) {
                minNumber = ele;
            }
            if (ele > maxNumber) {
                maxNumber = ele;
            }
        }
        this.maximumDifference = Math.abs(maxNumber - minNumber);
    }
} // End of Difference class

public class Day14 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        sc.close();

        Difference difference = new Difference(a);

        difference.computeDifference();

        System.out.print(difference.maximumDifference);
    }
}
