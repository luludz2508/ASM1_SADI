import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class EnrolmentManager {
    static Scanner in = new Scanner(System.in);

    //static Course c[] = new Course[100];
    static ArrayList<Course> courseAv = new ArrayList<>();
    static ArrayList<Student> studentAv = new ArrayList<>();
    static ArrayList<StudentEnrollment> Enrolments = new ArrayList<>();

    public static void mainMenu() {
        System.out.println("\n Enrollment System:");
        System.out.println("**********************");
        System.out.println("[1] Enrollment");
        System.out.println("[2] Display Enrolment by specific variable");
        System.out.println("[3] Display all enrolments");
        System.out.println("[4] Update or Delete enrolments of one student");
        System.out.println("[5] Save all change to file");
        System.out.println("[0] Exit. Remember to save changes to file by [5]");
    }

    public static char getMenuOption() {
        char option;
        Scanner in = new Scanner(System.in);
        System.out.print("Please enter your choice: ");
        option = in.next().charAt(0);
        return option;
    }

    public static void main(String[] args) {
        String fileName=readFile();
        int opt = 0;
        while (opt != '0') {
            mainMenu();
            opt = getMenuOption();
            switch (opt) {
                case '1':
                    add();
                    break;
                case '2':
                    getOne();
                    break;
                case '3':
                    getAll();
                    break;
                case '4':
                    update();
                    break;
                case '5':
                    SaveFile(fileName);
                    break;
                case '0':
                    System.out.println("Goodbye");
                    break;
                default:
                    System.out.println("Choice not found");
                    break;
            }
        }
    }


    public static void add() {

        Student result1 = null;
        Course result2 = null;

        System.out.println("Enter the student ID: ");
        String studentID = in.nextLine();


        for (Student student : studentAv) {
            if (student.getSid().equals(studentID))
                result1 = student;
        }
        if (result1 == null) {
            System.out.println("No student available with given ID.");
            return;
        }

        System.out.println("Enter the course ID: ");
        String courseID = in.nextLine();


        for (Course course : courseAv) {
            if (course.getCid().equals(courseID))
                result2 = course;
        }
        if (result2 == null) {
            System.out.println("No Course available with given ID.");
            return;
        }
        System.out.println("Enter the semester: ");
        String Sem = in.nextLine();

        StudentEnrollment a = new StudentEnrollment(result1, result2, Sem);
        System.out.println(a.toString());
        Enrolments.add(a);
    }


    public static void update() {
        Student result1 = null;
        String chose;
        for (Student student : studentAv) {
            System.out.println(student.getSid());
        }

        System.out.println("Enter the student ID: ");
        String studentID = in.nextLine();


        for (Student student : studentAv) {
            if (student.getSid().equals(studentID))
                result1 = student;
        }
        if (result1 == null) {
            System.out.println("No student available with given ID.");
            return;
        }
        for (StudentEnrollment enrolment : Enrolments) {
            if (enrolment.getStudent().getSid().equals(studentID)) {
                System.out.print(enrolment.toString());
            }
        }
        System.out.println("Enter the semester: ");
        String Sem = in.nextLine();
        for (StudentEnrollment enrolment : Enrolments) {
            if (enrolment.getStudent().getSid().equals(studentID) && enrolment.getSemester().equals(Sem)) {
                System.out.print(enrolment.toString());
            }
        }

        System.out.println("Do you want to | [1]Update / [2]Delete | a course? ");
        System.out.println("Please input your select");
        chose = in.nextLine();

        if (chose.equals("1")) {
            UpdateCourse(result1, Sem);
        } else {
            if (chose.equals("2")) {
                Delete(studentID, Sem);
            } else
                System.out.println("Not an available option.");
        }

    }

    public static void SaveFile(String fileName) {
        String defaultFile = "src/default.csv";
        if (!fileName.equals("")) {
            defaultFile = fileName;
        }
        try {
            FileWriter myWriter = new FileWriter(defaultFile);
            for (StudentEnrollment enrollment : Enrolments) {
                myWriter.write(enrollment.toString());
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void UpdateCourse(Student student, String semester) {
        for (Course course : courseAv) {
            System.out.println(course.getCid());
        }
        Course courseInput = null;
        System.out.println("Enter the course ID: ");
        String courseID = in.nextLine();


        for (Course course : courseAv) {
            if (course.getCid().equals(courseID)) {
                courseInput = course;
                break;
            }
        }
        if (courseInput == null) {
            System.out.println("No Course available with given ID.");
            return;
        }
        StudentEnrollment a = new StudentEnrollment(student, courseInput, semester);
        System.out.println(a.toString());
        Enrolments.add(a);
    }

    public static void Delete(String studentID, String Sem) {
        boolean deleted=false;
        for (StudentEnrollment enrolment : Enrolments) {
            if (enrolment.getStudent().getSid().equals(studentID) && enrolment.getSemester().equals(Sem)) {
                System.out.println(enrolment.getCourse().getCid());
                assert false;
            }

        }
        System.out.println("Please enter the course ID you want to delete:");
        String courseID = in.nextLine();

        for (StudentEnrollment enrolment : Enrolments) {
            if (enrolment.getStudent().getSid().equals(studentID) && enrolment.getSemester().equals(Sem) && enrolment.getCourse().getCid().equals(courseID)) {
                System.out.println("Successfully delete: " + courseID + " from " + studentID);
                Enrolments.remove(enrolment);
                deleted=true;
                break;
            }
        }
        if (!deleted){
            System.out.println("Cannot delete " + courseID + " from " + studentID);
        }
    }

    public static void getOne() {
        Student result1 = null;
        Course result2 = null;
        String Sem, choice;
        System.out.println("Choose one option below to search data:\n" +
                "   1.Print all courses for 1 student in 1 semester.\n" +
                "   2.Print all students of 1 course in 1 semester.\n" +
                "   3.Prints all courses offered in 1 semester");
        String option = in.nextLine();
        switch (option) {
            case "1":
                System.out.println("Enter the student ID: ");
                String studentID = in.nextLine();

                for (Student student : studentAv) {
                    if (student.getSid().equals(studentID))
                        result1 = student;
                }
                if (result1 == null) {
                    System.out.println("No student available with given ID.");
                    return;
                }
                System.out.println("Enter the semester: ");
                Sem = in.nextLine();

                for (StudentEnrollment enrolment : Enrolments) {
                    if (enrolment.getStudent().getSid().equals(studentID) && enrolment.getSemester().equals(Sem)) {
                        System.out.print(enrolment.toString());
                    }
                }
                System.out.println("Do you want to print to file?\n 1:Yes\n Else:No");
                choice = in.nextLine();
                if (choice.equals("1")) {
                    String defaultFile = "src/Report.csv";
                    try {
                        FileWriter myWriter = new FileWriter(defaultFile);
                        for (StudentEnrollment enrolment : Enrolments) {
                            if (enrolment.getStudent().getSid().equals(studentID) && enrolment.getSemester().equals(Sem)) {
                                myWriter.write(enrolment.toString());
                            }
                        }
                        myWriter.close();
                        System.out.println("Successfully wrote to the file.");
                    } catch (IOException e) {
                        System.out.println("An error occurred.");
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("Finished");
                }

                break;
            case "2":
                System.out.println("Enter the Course ID: ");
                String courseID = in.nextLine();


                for (Course course : courseAv) {
                    if (course.getCid().equals(courseID))
                        result2 = course;
                }
                if (result2 == null) {
                    System.out.println("No course available with given ID.");
                    return;
                }
                System.out.println("Enter the semester: ");
                Sem = in.nextLine();

                for (StudentEnrollment enrolment : Enrolments) {
                    if (enrolment.getCourse().getCid().equals(courseID) && enrolment.getSemester().equals(Sem)) {
                        System.out.print(enrolment.toString());
                    }
                }

                System.out.println("Do you want to print to file?\n 1:Yes\n Else:No");
                choice = in.nextLine();
                if (choice.equals("1")) {
                    String defaultFile = "src/Report.csv";
                    try {
                        FileWriter myWriter = new FileWriter(defaultFile);
                        for (StudentEnrollment enrolment : Enrolments) {
                            if (enrolment.getCourse().getCid().equals(courseID) && enrolment.getSemester().equals(Sem)) {
                                myWriter.write(enrolment.toString());
                            }
                        }
                        myWriter.close();
                        System.out.println("Successfully wrote to the file.");
                    } catch (IOException e) {
                        System.out.println("An error occurred.");
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("Finished");
                }
                break;
            case "3":
                System.out.println("Enter the semester: ");
                Sem = in.nextLine();

                for (StudentEnrollment enrolment : Enrolments) {
                    if (enrolment.getSemester().equals(Sem)) {
                        System.out.print(enrolment.toString());
                    }
                }
                System.out.println("Do you want to print to file?\n 1:Yes\n Else:No");
                choice = in.nextLine();
                if (choice.equals("1")) {
                    String defaultFile = "src/Report.csv";
                    try {
                        FileWriter myWriter = new FileWriter(defaultFile);
                        for (StudentEnrollment enrolment : Enrolments) {
                            if (enrolment.getSemester().equals(Sem)) {
                                myWriter.write(enrolment.toString());
                            }
                        }
                        myWriter.close();
                        System.out.println("Successfully wrote to the file.");
                    } catch (IOException e) {
                        System.out.println("An error occurred.");
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("Finished");
                }
                break;
            default:
                System.out.println("Unavailable choice.");
                break;
        }
    }

    public static String readFile() {
        boolean done = false;
        String fileName = "";
        while (!done) {
            System.out.print("Please enter File Name located in src folder. Leave null to load default file: ");
            fileName = in.nextLine();
            String defaultFile = "src/default.csv";
            if (!fileName.equals("")) {
                defaultFile = "src/"+fileName;
            }
            String line;
            String splitBy = ",";
            try {
                BufferedReader in = new BufferedReader(new FileReader(defaultFile));

                while ((line = in.readLine()) != null) {

                    boolean sAdded = false, cAdded = false;
                    String[] data = line.split(splitBy);
                    for (Student student : studentAv) {
                        if (student.getSid().equals(data[0])) {
                            sAdded = true;
                            break;
                        }
                    }
                    for (Course course : courseAv) {
                        if (course.getCid().equals(data[3])) {
                            cAdded = true;
                            break;
                        }
                    }
                    if (!sAdded) {
                        studentAv.add(new Student(data[0], data[1], data[2]));
                    }
                    if (!cAdded) {
                        courseAv.add(new Course(data[3], data[4], data[5]));
                    }
                    Enrolments.add(new StudentEnrollment(new Student(data[0], data[1], data[2]), new Course(data[3], data[4], data[5]), data[6]));
                }
                System.out.println("Successfully read from file.");
                done = true;

            } catch (IOException e) {
                System.out.println("An error occurred. Cannot find File.");

                done=false;
            }
        }
        return fileName;
    }

    public static void getAll() {
        if (Enrolments.size() == 0) {
            System.out.println("Nothing to show");
            return;
        }
        for (StudentEnrollment a : Enrolments) {
            System.out.print(a.toString());
        }
    }
}
