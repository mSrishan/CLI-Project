import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

      optionSelect();

    }
    public static void optionSelect(){
        Scanner input = new Scanner(System.in);
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("|                    WELCOME  TO  MARKS  MANAGEMENT SYSTEM                    |");
        System.out.println("-------------------------------------------------------------------------------");

        System.out.println("[1] Add New Student                     [2] Add New Student With Marks");
        System.out.println("[3] Add Marks                           [4] Update Student Details");
        System.out.println("[5] Update Marks                        [6] Delete Student");
        System.out.println("[7] Print Student Details               [8] Print Student Ranks");
        System.out.println("[9] Best In Programming Fundamentals    [10] Best In Database Management Systems");
        System.out.println(" ");
        System.out.print("Enter an option to continue > ");
        int option = input.nextInt();
        selectOption(option);
    }

    static Scanner sc = new Scanner(System.in);

    public static void selectOption(int option) {
           switch (option){
               case 1:
                   addNewStudent();
                   break;
               case 2:
                   addNewStudentWithMarks();
           }
    }

    private static void addNewStudentWithMarks() {
        System.out.print("Enter student id : ");
        int studentId = sc.nextInt();
        System.out.print("Enter student name : ");
        String studentName = sc.next();
        System.out.println(" ");
        System.out.print("Programming Fundamentals Marks : ");
        String programmingFundamentalsMarks = sc.next();
        System.out.println("Database Management Systems Marks : ");
        String databaseManagementSystemMarks = sc.next();
    }

    private static void addNewStudent() {
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("|                    Add New Student                                          |");
        System.out.println("-------------------------------------------------------------------------------");
        System.out.print("Enter Student ID : ");
        int id =sc.nextInt();
        System.out.print("Enter Student Name : ");
        String name =sc.next();
        System.out.println(" ");

        String[] studentNames = new String[12];
        int[] studentIds = new int[12];

        for (int i = 0; i < studentIds.length; i++) {
            studentIds[i] = sc.nextInt();
            studentNames[i] = sc.next();
        }
        System.out.print("Student has been added successfully. Do you want to add a new student (y/n) : ");
        String answer = sc.next();

        if (answer.equalsIgnoreCase("y")) {
            addNewStudent();
        } else {
            optionSelect();
        }
    }


}