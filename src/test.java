import java.util.Scanner;

public class test {

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
                case 1:
                    addNewStudent(scanner);
                    break;
                case 2:
                    addNewStudentWithMarks(scanner);
                    break;
                case 3:
                    addMarks(scanner);
                    break;
                case 4:
                    updateStudentDetails(scanner);
                    break;
                case 5:
                    updateMarks(scanner);
                    break;
                case 6:
                    deleteStudent(scanner);
                    break;
                case 7:
                    printStudentDetails(scanner);
                    break;
                case 8:
                    printStudentRanks();
                    break;
                case 9:
                    bestInProgrammingFundamentals();
                    break;
                case 10:
                    bestInDatabaseManagementSystem();
                    break;
                case 0:
                    System.out.println("Exiting... Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
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
    }

    private static void addNewStudent(Scanner scanner) {
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
        // TODO: Implement functionality as per guidelines
    }

    private static void printStudentRanks() {
        // TODO: Implement functionality as per guidelines
    }

    private static void bestInProgrammingFundamentals() {
        // TODO: Implement functionality as per guidelines
    }

    private static void bestInDatabaseManagementSystem() {
        // TODO: Implement functionality as per guidelines
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
