package StudentGrade;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Student {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            ArrayList<String> studentNames = new ArrayList<>();
            ArrayList<Integer> studentGrades = new ArrayList<>();
            int maxMark;

            // Prompt for the examination name, number of students and maximum mark
            System.out.println("Welcome to the Grade Calculator!\n");
            System.out.println("Please enter the name of the examination:");
            String examName = scanner.nextLine();
            System.out.println("Please enter the Maximum Mark of the examination:");
            maxMark = scanner.nextInt();
            System.out.println("Please enter the number of students:");

            // Input number of students
            int numStudents = 0;
            while (true) {
                try {
                    numStudents = scanner.nextInt();
                    break;
                } catch (InputMismatchException e) {
                    // Check if the entered input is of valid data type
                    System.out.println("Invalid Input. Please enter a valid number of students:");
                    scanner.next(); // Clear the invalid input
                }
            }

            // Input names and grades for each student
            for (int i = 0; i < numStudents; i++) {
                System.out.print("Enter the name/register number of student " + (i + 1) + ": ");
                String name = scanner.next();
                studentNames.add(name);

                boolean validGrade = false;
                while (!validGrade) {
                    try {
                        int grade;
                        while (true) {
                            System.out.print("Enter the grade for student " + name + ": ");
                            grade = scanner.nextInt();
                            if (grade <= maxMark) {
                                break; // Break the loop if the grade is valid
                            } else {
                                System.out.println("Invalid input. The grade exceeds the maximum mark of " + maxMark);
                            }
                        }
                        studentGrades.add(grade);
                        validGrade = true;
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a valid grade.");
                        scanner.next(); // Clear the invalid input
                    }
                }
            }

            // Calculate  average grade
            double averageGrade = calculateAverage(studentGrades);

            // Find the highest and lowest grades
            int highestGrade = findHighestGrade(studentGrades);
            int lowestGrade = findLowestGrade(studentGrades);

            // Find students with highest grade
            ArrayList<String> highestScorers = findEqualScorers(studentNames, studentGrades, highestGrade);

            // Find students with lowest grade
            ArrayList<String> lowestScorers = findEqualScorers(studentNames, studentGrades, lowestGrade);

            // Display results
            System.out.println("\n" + examName + " Examination Summary:\n");
            System.out.println("Average Grade: " + averageGrade);
            System.out.println("\nHighest Scorer(s):");
            for (String scorer : highestScorers) {
                System.out.println(scorer + " with a grade of " + highestGrade);
            }
            System.out.println("\nLowest Scorer(s):");
            for (String scorer : lowestScorers) {
                System.out.println(scorer + " with a grade of " + lowestGrade);
            }
        }
    }

    // Method to calculate average grade
    public static double calculateAverage(ArrayList<Integer> studentGrades) {
        int sum = 0;
        for (int grade : studentGrades) {
            sum += grade;
        }
        return (double) sum / studentGrades.size();
    }

    // Method to find the highest grade
    public static int findHighestGrade(ArrayList<Integer> studentGrades) {
        int highestGrade = 0;
        for (int grade : studentGrades) {
            if (grade > highestGrade) {
                highestGrade = grade;
            }
        }
        return highestGrade;
    }

    // Method to find the lowest grade
    public static int findLowestGrade(ArrayList<Integer> studentGrades) {
        int lowestGrade = Integer.MAX_VALUE;
        for (int grade : studentGrades) {
            if (grade < lowestGrade) {
                lowestGrade = grade;
            }
        }
        return lowestGrade;
    }

    // Method to find students with a specific grade
    public static ArrayList<String> findEqualScorers(ArrayList<String> studentNames, ArrayList<Integer> studentGrades, int targetGrade) {
        ArrayList<String> scorers = new ArrayList<>();
        for (int i = 0; i < studentGrades.size(); i++) {
            if (studentGrades.get(i) == targetGrade) {
                scorers.add(studentNames.get(i));
            }
        }
        return scorers;
    }
}