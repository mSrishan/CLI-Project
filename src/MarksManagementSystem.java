import java.util.Scanner;

public class MarksManagementSystem {

    private static final int MAX_STUDENTS = 100;
    private static String[] studentIds = new String[MAX_STUDENTS];
    private static String[] studentNames = new String[MAX_STUDENTS];
    private static int[] programmingMarks = new int[MAX_STUDENTS];
    private static int[] databaseMarks = new int[MAX_STUDENTS];
    private static int studentCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            displayMenu();
            int option = getIntInput(scanner, "Select an option: ");

            switch (option) {
                case 1 -> addNewStudent(scanner);
                case 2 -> addNewStudentWithMarks(scanner);
                case 3 -> addMarks(scanner);
                case 4 -> updateStudentDetails(scanner);
                case 5 -> updateMarks(scanner);
                case 6 -> deleteStudent(scanner);
                case 7 -> printStudentDetails(scanner);
                case 8 -> printStudentRanks();
                case 9 -> bestInProgrammingFundamentals();
                case 10 -> bestInDatabaseManagementSystem();
                case 0 -> {
                    System.out.println("Exiting... Goodbye!");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("|                    WELCOME  TO  MARKS  MANAGEMENT SYSTEM                    |");
        System.out.println("-------------------------------------------------------------------------------");

        System.out.println("[1] Add New Student                     [2] Add New Student With Marks");
        System.out.println("[3] Add Marks                           [4] Update Student Details");
        System.out.println("[5] Update Marks                        [6] Delete Student");
        System.out.println("[7] Print Student Details               [8] Print Student Ranks");
        System.out.println("[9] Best In Programming Fundamentals    [10] Best In Database Management Systems");
        System.out.println("[0] Exit");
    }

    private static void addNewStudent(Scanner scanner) {
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("|                    ADD NEW STUDENT                                          |");
        System.out.println("-------------------------------------------------------------------------------");
        while (true) {
            String id = getStringInput(scanner, "Enter Student ID: ");
            if (findStudentIndex(id) != -1) {
                System.out.println("Student ID already exists. Try again.");
                continue;
            }
            String name = getStringInput(scanner, "Enter Student Name: ");
            addStudent(id, name, -1, -1);
            System.out.println("Student added successfully!");

            if (!askContinue(scanner, "Add another student? (yes/no): ")) {
                break;
            }
        }
    }

    private static void addNewStudentWithMarks(Scanner scanner) {
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("|                    ADD NEW STUDENT WITH MARKS                               |");
        System.out.println("-------------------------------------------------------------------------------");
        while (true) {
            String id = getStringInput(scanner, "Enter Student ID: ");
            if (findStudentIndex(id) != -1) {
                System.out.println("Student ID already exists. Try again.");
                continue;
            }
            String name = getStringInput(scanner, "Enter Student Name: ");
            int progMarks = getValidatedMarks(scanner, "Enter Programming Fundamentals Marks (0-100): ");
            int dbMarks = getValidatedMarks(scanner, "Enter Database Management System Marks (0-100): ");
            addStudent(id, name, progMarks, dbMarks);
            System.out.println("Student with marks added successfully!");

            if (!askContinue(scanner, "Add another student? (yes/no): ")) {
                break;
            }
        }
    }

    private static void addMarks(Scanner scanner) {
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("|                    ADD MARKS                                                |");
        System.out.println("-------------------------------------------------------------------------------");
        while (true) {
            String id = getStringInput(scanner, "Enter Student ID to add marks: ");
            int index = findStudentIndex(id);
            if (index == -1) {
                System.out.println("Student ID not found. Try again.");
                continue;
            }
            if (programmingMarks[index] != -1) {
                System.out.println("Marks already added for this student.");
                break;
            }
            programmingMarks[index] = getValidatedMarks(scanner, "Enter Programming Fundamentals Marks (0-100): ");
            databaseMarks[index] = getValidatedMarks(scanner, "Enter Database Management System Marks (0-100): ");
            System.out.println("Marks added successfully!");

            if (!askContinue(scanner, "Add marks for another student? (yes/no): ")) {
                break;
            }
        }
    }

    private static void updateStudentDetails(Scanner scanner) {
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("|                    UPDATE STUDENT DETAILS                                   |");
        System.out.println("-------------------------------------------------------------------------------");
        while (true) {
            String id = getStringInput(scanner, "Enter Student ID to update: ");
            int index = findStudentIndex(id);
            if (index == -1) {
                System.out.println("Student ID not found. Try again.");
                continue;
            }
            String newName = getStringInput(scanner, "Enter new name for the student: ");
            studentNames[index] = newName;
            System.out.println("Student details updated successfully!");

            if (!askContinue(scanner, "Update another student? (yes/no): ")) {
                break;
            }
        }
    }

    private static void updateMarks(Scanner scanner) {
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("|                    UPDATE MARKS                                             |");
        System.out.println("-------------------------------------------------------------------------------");
        while (true) {
            String id = getStringInput(scanner, "Enter Student ID to update marks: ");
            int index = findStudentIndex(id);
            if (index == -1) {
                System.out.println("Student ID not found. Try again.");
                continue;
            }
            programmingMarks[index] = getValidatedMarks(scanner, "Enter new Programming Fundamentals Marks (0-100): ");
            databaseMarks[index] = getValidatedMarks(scanner, "Enter new Database Management System Marks (0-100): ");
            System.out.println("Marks updated successfully!");

            if (!askContinue(scanner, "Update marks for another student? (yes/no): ")) {
                break;
            }
        }
    }

    private static void deleteStudent(Scanner scanner) {
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("|                    DELETE STUDENT                                           |");
        System.out.println("-------------------------------------------------------------------------------");
        while (true) {
            String id = getStringInput(scanner, "Enter Student ID to delete: ");
            int index = findStudentIndex(id);
            if (index == -1) {
                System.out.println("Student ID not found. Try again.");
                continue;
            }
            removeStudent(index);
            System.out.println("Student deleted successfully!");

            if (!askContinue(scanner, "Delete another student? (yes/no): ")) {
                break;
            }
        }
    }

    private static void printStudentDetails(Scanner scanner) {
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("|                    PRINT STUDENT DETAILS                                    |");
        System.out.println("-------------------------------------------------------------------------------");
        String id = getStringInput(scanner, "Enter Student ID: ");
        int index = findStudentIndex(id);
        if (index == -1) {
            System.out.println("Student ID not found.");
            return;
        }
        System.out.println("Student ID: " + studentIds[index]);
        System.out.println("Name: " + studentNames[index]);
        if (programmingMarks[index] == -1 || databaseMarks[index] == -1) {
            System.out.println("Marks not yet added.");
        } else {
            System.out.println("Programming Marks: " + programmingMarks[index]);
            System.out.println("Database Marks: " + databaseMarks[index]);
            System.out.println("Total Marks: " + (programmingMarks[index] + databaseMarks[index]));
            System.out.println("Average Marks: " + (programmingMarks[index] + databaseMarks[index]) / 2.0);
        }
    }

    private static void printStudentRanks() {
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("|                    PRINT STUDENT RANKS                                      |");
        System.out.println("-------------------------------------------------------------------------------");
        if (studentCount == 0) {
            System.out.println("No students available.");
            return;
        }
        int[] totalMarks = new int[studentCount];
        for (int i = 0; i < studentCount; i++) {
            totalMarks[i] = programmingMarks[i] + databaseMarks[i];
        }
        for (int i = 0; i < studentCount - 1; i++) {
            for (int j = 0; j < studentCount - i - 1; j++) {
                if (totalMarks[j] < totalMarks[j + 1]) {
                    swap(j, j + 1, totalMarks);
                }
            }
        }
        System.out.println("Rank\tStudent ID\tName\tTotal Marks\tAverage Marks");
        for (int i = 0; i < studentCount; i++) {
            if (programmingMarks[i] == -1 || databaseMarks[i] == -1) continue;
            System.out.printf("%d\t%s\t%s\t%d\t%.2f%n",
                    i + 1, studentIds[i], studentNames[i], totalMarks[i], totalMarks[i] / 2.0);
        }
    }

    private static void bestInProgrammingFundamentals() {
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("|                    BEST IN PROGRAMMING FUNDAMENTALS                         |");
        System.out.println("-------------------------------------------------------------------------------");
        int maxMarks = -1;
        String bestStudent = "No data";
        for (int i = 0; i < studentCount; i++) {
            if (programmingMarks[i] > maxMarks) {
                maxMarks = programmingMarks[i];
                bestStudent = studentNames[i];
            }
        }
        System.out.println("Best in Programming Fundamentals: " + bestStudent + " (" + maxMarks + " marks)");
    }

    private static void bestInDatabaseManagementSystem() {
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("|                    BEST IN DATABASE MANAGEMENT SYSTEM                       |");
        System.out.println("-------------------------------------------------------------------------------");
        int maxMarks = -1;
        String bestStudent = "No data";
        for (int i = 0; i < studentCount; i++) {
            if (databaseMarks[i] > maxMarks) {
                maxMarks = databaseMarks[i];
                bestStudent = studentNames[i];
            }
        }
        System.out.println("Best in Database Management Systems: " + bestStudent + " (" + maxMarks + " marks)");
    }

    private static void addStudent(String id, String name, int progMarks, int dbMarks) {
        studentIds[studentCount] = id;
        studentNames[studentCount] = name;
        programmingMarks[studentCount] = progMarks;
        databaseMarks[studentCount] = dbMarks;
        studentCount++;
    }

    private static int findStudentIndex(String id) {
        for (int i = 0; i < studentCount; i++) {
            if (studentIds[i].equals(id)) {
                return i;
            }
        }
        return -1;
    }

    private static void removeStudent(int index) {
        for (int i = index; i < studentCount - 1; i++) {
            studentIds[i] = studentIds[i + 1];
            studentNames[i] = studentNames[i + 1];
            programmingMarks[i] = programmingMarks[i + 1];
            databaseMarks[i] = databaseMarks[i + 1];
        }
        studentCount--;
    }

    private static void swap(int i, int j, int[] totalMarks) {
        int tempMarks = totalMarks[i];
        totalMarks[i] = totalMarks[j];
        totalMarks[j] = tempMarks;

        String tempId = studentIds[i];
        studentIds[i] = studentIds[j];
        studentIds[j] = tempId;

        String tempName = studentNames[i];
        studentNames[i] = studentNames[j];
        studentNames[j] = tempName;

        int tempProg = programmingMarks[i];
        programmingMarks[i] = programmingMarks[j];
        programmingMarks[j] = tempProg;

        int tempDb = databaseMarks[i];
        databaseMarks[i] = databaseMarks[j];
        databaseMarks[j] = tempDb;
    }

    private static int getValidatedMarks(Scanner scanner, String message) {
        int marks;
        do {
            marks = getIntInput(scanner, message);
            if (marks < 0 || marks > 100) {
                System.out.println("Invalid marks. Please enter a value between 0 and 100.");
            }
        } while (marks < 0 || marks > 100);
        return marks;
    }

    private static boolean askContinue(Scanner scanner, String message) {
        String response = getStringInput(scanner, message);
        return response.equalsIgnoreCase("yes");
    }

    private static int getIntInput(Scanner scanner, String message) {
        System.out.print(message);
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Please enter a number: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private static String getStringInput(Scanner scanner, String message) {
        System.out.print(message);
        return scanner.next();
    }
}
