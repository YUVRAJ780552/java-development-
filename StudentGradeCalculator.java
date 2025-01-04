import java.util.Scanner;

public class StudentGradeCalculator {
    public static void main(String[] args) {
    
        try (Scanner scanner = new Scanner(System.in)) {
        
            System.out.print("Enter the number of subjects: ");
            int numSubjects = scanner.nextInt();
            
            int[] marks = new int[numSubjects];
            
            System.out.println("Enter marks obtained (out of 100) for each subject:");
            for (int i = 0; i < numSubjects; i++) {
                System.out.print("Subject " + (i + 1) + ": ");
                marks[i] = scanner.nextInt();
            }

            int totalMarks = 0;
            for (int mark : marks) {
                totalMarks += mark;
            }
            
            double averagePercentage = (double) totalMarks / numSubjects;
        
            String grade;
            if (averagePercentage >= 90) {
                grade = "A";
            } else if (averagePercentage >= 80) {
                grade = "B";
            } else if (averagePercentage >= 70) {
                grade = "C";
            } else if (averagePercentage >= 60) {
                grade = "D";
            } else {
                grade = "F";
            }
            
            System.out.println("\n--- Results ---");
            System.out.println("Total Marks: " + totalMarks);
            System.out.printf("Average Percentage: %.2f%%\n", averagePercentage);
            System.out.println("Grade: " + grade);
        }
    }
}