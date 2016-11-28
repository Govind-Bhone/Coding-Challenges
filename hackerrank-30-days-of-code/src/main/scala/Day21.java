/**
 * Created by govind.bhone on 11/22/2016.
 */

import java.lang.reflect.Method;

class Day21 {
    //Write your code here

    public static <T> void printArray(T[] x) {
        for (int i = 0; i < x.length; i++) {
            System.out.println(x[i]);
        }
    }

    public static <T> T last(T[] x){
        T last=x[0] ;
        for (int i = 0; i < x.length; i++) {
            last =x[i];
        }
        return last;
    }

    public static void main(String args[]) {
        Integer[] intArray = {1, 2, 3};
        String[] stringArray = {"Hello", "World"};

        printArray(intArray);
        printArray(stringArray);
        System.out.println(last(intArray));

        if (Solution.class.getDeclaredMethods().length > 2) {
            System.out.println("You should only have 1 method named printArray.");
        }
    }
}