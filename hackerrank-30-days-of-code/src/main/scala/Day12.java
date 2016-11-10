/**
 * Created by govind.bhone on 11/10/2016.
 */

import java.util.*;

class Person1 {
    protected String firstName;
    protected String lastName;
    protected int idNumber;

    // Constructor
    Person1(String firstName, String lastName, int identification) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.idNumber = identification;
    }

    // Print person data
    public void printPerson() {
        System.out.println(
                "Name: " + lastName + ", " + firstName
                        + "\nID: " + idNumber);
    }

}

class Student extends Person1 {
    private int[] testScores;

    Student(String fName, String lName, int id, int[] scores) {
        super(fName, lName, id);
        this.firstName = fName;
        this.lastName = lName;
        this.idNumber = id;
        this.testScores = scores;
    }

    public char calculate() {
        int sum = 0;
        for (int i = 0;
             i < testScores.length;
             i++) {
            sum += testScores[i];
        }
        int average = sum / testScores.length;

        if (average >= 90 && average <= 100) {
            return 'O';
        } else if (average >= 80 && average < 90) {
            return 'E';
        } else if (average >= 70 && average < 80) {
            return 'A';
        } else if (average >= 55 && average < 70) {
            return 'P';
        } else if (average >= 40 && average < 55) {
            return 'D';
        } else if (average < 40) {
            return 'T';
        } else {
            return 'W';
        }
    }

}

public class Day12 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String firstName = scan.next();
        String lastName = scan.next();
        int id = scan.nextInt();
        int numScores = scan.nextInt();
        int[] testScores = new int[numScores];
        for (int i = 0;
             i < numScores;
             i++) {
            testScores[i] = scan.nextInt();
        }
        scan.close();

        Student s = new Student(firstName, lastName, id, testScores);
        s.printPerson();
        System.out.println("Grade: " + s.calculate());
    }
}
