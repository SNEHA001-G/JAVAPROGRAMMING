import javax.swing.*;

public class StudentGradeCalculator {

    public static void main(String[] args) {
        // Create a pop-up dialog for input (student's roll number)
        String rollNumber = JOptionPane.showInputDialog("Enter your Roll Number:");

        // Create a pop-up dialog for the number of subjects
        String input = JOptionPane.showInputDialog("Enter the number of subjects:");
        int numSubjects = Integer.parseInt(input);

        String[] subjects = new String[numSubjects];
        double[] marks = new double[numSubjects];
        double totalMarks = 0;

        // Loop to get subject names and marks for each subject
        for (int i = 0; i < numSubjects; i++) {
            // Get the subject name
            subjects[i] = JOptionPane.showInputDialog("Enter the name of subject " + (i + 1) + ":");    
            
            double subjectMarks = -1;
            boolean validMarks = false;

            // Keep prompting until valid marks are entered
            while (!validMarks) {
                input = JOptionPane.showInputDialog("Enter marks for " + subjects[i] + " (out of 100):");
                try {
                    subjectMarks = Double.parseDouble(input);

                    // Check if marks are valid (between 0 and 100)
                    if (subjectMarks >= 0 && subjectMarks <= 100) {
                        validMarks = true;  // Exit the loop if valid marks
                    } else {
                        // Show error message and ask again if invalid
                        JOptionPane.showMessageDialog(null,
                                "Invalid marks! Please enter marks between 0 and 100.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException e) {
                    // If the user enters a non-numeric value
                    JOptionPane.showMessageDialog(null,
                            "Please enter a valid numeric value for marks.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }

            marks[i] = subjectMarks;
            totalMarks += marks[i];
        }

        // Calculate average percentage
        double averagePercentage = (totalMarks / (numSubjects * 100)) * 100;

        // Determine grade
        String grade = getGrade(averagePercentage);

        // Display the results in a popup
        StringBuilder resultMessage = new StringBuilder();
        resultMessage.append("Roll Number: ").append(rollNumber).append("\n\n");
        
        for (int i = 0; i < numSubjects; i++) {
            resultMessage.append(subjects[i]).append(": ").append(marks[i]).append("/100\n");
        }
        resultMessage.append("\nTotal Marks: ").append(totalMarks).append(" out of ").append(numSubjects * 100)
                .append("\nAverage Percentage: ").append(averagePercentage).append("%")
                .append("\nGrade: ").append(grade);

        JOptionPane.showMessageDialog(null,
                resultMessage.toString(),
                "Result",
                JOptionPane.INFORMATION_MESSAGE);
    }

    // Method to return grade based on average percentage
    public static String getGrade(double percentage) {
        if (percentage >= 90) {
            return "A+";
        } else if (percentage >= 80) {
            return "A";
        } else if (percentage >= 70) {
            return "B+";
        } else if (percentage >= 60) {
            return "B";
        } else if (percentage >= 50) {
            return "C";
        } else {
            return "F";
        }
    }
}
