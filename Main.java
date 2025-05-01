import java.util.*;

class Course {
    String courseCode;
    String title;
    String description;
    int capacity;
    int enrolledStudents;

    public Course(String courseCode, String title, String description, int capacity) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.enrolledStudents = 0;
    }

    // Method to register a student in the course
    public boolean registerStudent() {
        if (enrolledStudents < capacity) {
            enrolledStudents++;
            return true;
        } else {
            return false;
        }
    }

    // Method to remove a student from the course
    public boolean removeStudent() {
        if (enrolledStudents > 0) {
            enrolledStudents--;
            return true;
        } else {
            return false;
        }
    }

    public String toString() {
        return "Course Code: " + courseCode + "\nTitle: " + title + "\nDescription: " + description +
               "\nCapacity: " + capacity + "\nEnrolled Students: " + enrolledStudents;
    }
}

class Student {
    String studentID;
    String name;
    Set<String> registeredCourses;

    public Student(String studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.registeredCourses = new HashSet<>();
    }

    // Method to register a student for a course
    public boolean registerForCourse(Course course) {
        if (course.registerStudent()) {
            registeredCourses.add(course.courseCode);
            return true;
        }
        return false;
    }

    // Method to drop a course for the student
    public boolean dropCourse(Course course) {
        if (registeredCourses.contains(course.courseCode) && course.removeStudent()) {
            registeredCourses.remove(course.courseCode);
            return true;
        }
        return false;
    }

    public String toString() {
        return "Student ID: " + studentID + "\nName: " + name + "\nRegistered Courses: " + registeredCourses;
    }
}

class StudentCourseRegistrationSystem {
    Map<String, Course> courses;
    Map<String, Student> students;

    public StudentCourseRegistrationSystem() {
        courses = new HashMap<>();
        students = new HashMap<>();
    }

    // Method to add a new course
    public void addCourse(String courseCode, String title, String description, int capacity) {
        courses.put(courseCode, new Course(courseCode, title, description, capacity));
    }

    // Method to add a new student
    public void addStudent(String studentID, String name) {
        students.put(studentID, new Student(studentID, name));
    }

    // Method to display all available courses
    public void displayCourses() {
        for (Course course : courses.values()) {
            System.out.println(course);
            System.out.println("------------------------------------------------");
        }
    }

    // Method to register a student for a course
    public void registerStudentForCourse(String studentID, String courseCode) {
        Student student = students.get(studentID);
        Course course = courses.get(courseCode);
        if (student != null && course != null) {
            if (student.registerForCourse(course)) {
                System.out.println(student.name + " successfully registered for " + course.title);
            } else {
                System.out.println("Failed to register " + student.name + " for " + course.title + ". Course is full.");
            }
        } else {
            System.out.println("Invalid student or course.");
        }
    }

    // Method to drop a course for a student
    public void dropStudentFromCourse(String studentID, String courseCode) {
        Student student = students.get(studentID);
        Course course = courses.get(courseCode);
        if (student != null && course != null) {
            if (student.dropCourse(course)) {
                System.out.println(student.name + " successfully dropped " + course.title);
            } else {
                System.out.println("Failed to drop " + course.title + " for " + student.name + ". Not registered.");
            }
        } else {
            System.out.println("Invalid student or course.");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentCourseRegistrationSystem system = new StudentCourseRegistrationSystem();

        // Add some courses
        system.addCourse("CS101", "Introduction to Computer Science", "Learn the basics of programming.", 3);
        system.addCourse("MATH101", "Calculus I", "Fundamentals of calculus.", 2);
        system.addCourse("PHYS101", "Physics I", "Introduction to classical mechanics.", 5);

        // Add some students
        system.addStudent("S001", "John Doe");
        system.addStudent("S002", "Jane Smith");
        system.addStudent("S003", "Mark Johnson");

        // Main menu for user interaction
        while (true) {
            System.out.println("\n1. Display Courses");
            System.out.println("2. Register for a Course");
            System.out.println("3. Drop a Course");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character

            switch (choice) {
                case 1:
                    system.displayCourses();
                    break;

                case 2:
                    System.out.print("Enter Student ID: ");
                    String studentID = scanner.nextLine();
                    System.out.print("Enter Course Code: ");
                    String courseCode = scanner.nextLine();
                    system.registerStudentForCourse(studentID, courseCode);
                    break;

                case 3:
                    System.out.print("Enter Student ID: ");
                    studentID = scanner.nextLine();
                    System.out.print("Enter Course Code: ");
                    courseCode = scanner.nextLine();
                    system.dropStudentFromCourse(studentID, courseCode);
                    break;

                case 4:
                    System.out.println("Exiting system. Goodbye!");
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
