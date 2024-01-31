package lmsTask;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static UserDatabase userDatabase = new UserDatabase();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println(getTime());
        System.out.println(getDayMessage());

        while (true) {
            System.out.println("1. If you have an account\n 2. If you lost your password and want to change it");
            int userAnsw1 = scanner.nextInt();
            if (userAnsw1 == 1) {
                handleUserAccount();
            } else if (userAnsw1 == 2) {
                handleUserPassword();
            }
        }
    }

    public static LocalTime getTime() {
        return LocalTime.now();
    }

    public static String getDayMessage() {
        int hour = getTime().getHour();
        int minute = getTime().getMinute();

        if (hour < 12) {
            return "Good morning! Hour-> " + hour + ":" + minute;
        } else if (hour < 18) {
            return "Good Afternoon! Hour-> " + hour + ":" + minute;
        } else {
            return "Good Night! Hour-> " + hour + ":" + minute;
        }
    }

    public static void handleUserAccount() {
        System.out.println("Enter email:");
        String email = scanner.next();
        System.out.println("Enter password:");
        String password = scanner.next();
        if (userDatabase.userExist(email,password)){
            actions();
        }

    }

    public static void handleUserPassword() {
        System.out.println("Enter email to change password:");
        String email = scanner.next();
        System.out.println("Enter new password(more than 7 symbols):");
        String password = scanner.next();
        boolean passwordChanged=userDatabase.updateUserPassword(email,password);
        if (passwordChanged) {
            actions();
        }

    }

    public static  void actions(){
        do {
            System.out.println("Choose one action");
            System.out.println("1. ->Add new group");
            System.out.println("2. ->Get group by name");
            System.out.println("3. ->Update group name");
            System.out.println("4. ->Get all groups");
            System.out.println("5. ->Add new student to group");
            System.out.println("6. ->Update student");
            System.out.println("7. ->Find student by first name");
            System.out.println("8. ->Get all students by group name");
            System.out.println("9. ->Get all student's lesson");
            System.out.println("10. ->Delete student");
            System.out.println("11. ->Add new lesson to group");
            System.out.println("12. ->Get lesson by name");
            System.out.println("13. ->Get all lesson by group name");
            System.out.println("14. ->Delete lesson");
            System.out.println("15. ->Delete group");

            int choice=scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("Enter group name:");
                    String groupName = scanner.nextLine();
                    System.out.println("Enter group description:");
                    String description = scanner.next();
                    Groups groups = new Groups(groupName, description, new ArrayList<>(), new ArrayList<>());
                    userDatabase.addNewGroup(groups);
                    System.out.println(groups);
                    break;
                case 2:
                    scanner.nextLine();
                    System.out.println("Enter group name:");
                    String groupName1 = scanner.nextLine();
                    Groups group = userDatabase.getGroupByName(groupName1);
                    System.out.println(group);
                    break;
                case 3:
                    scanner.nextLine();
                    System.out.println("Enter group name:");
                    String groupName2 = scanner.nextLine();
                    System.out.println("Enter new name of group:");
                    String newGroupName = scanner.nextLine();
                    userDatabase.updateGroupName(groupName2, newGroupName);
                    break;
                case 4:
                    scanner.nextLine();
                    System.out.println("All groups:\n");
                    System.out.println(userDatabase.getAllGroups());
                    break;
                case 5:
                    System.out.println("Enter name of group to add student:");
                    String groupName3 = scanner.nextLine();
                    System.out.println("Enter first name:");
                    String firstName = scanner.nextLine();
                    System.out.println("Enter last name:");
                    String lastName = scanner.nextLine();
                    System.out.println("Enter email:");
                    String email = scanner.nextLine();
                    System.out.println("Enter password:");
                    String password = scanner.nextLine();
                    System.out.println("Enter gender (MALE/FEMALE):");
                    String genderInput = scanner.nextLine().toUpperCase();

                    Gender gender;
                    switch (genderInput) {
                        case "MALE":
                            gender = Gender.MALE;
                            break;
                        case "FEMALE":
                            gender = Gender.FEMALE;
                            break;
                        default:
                            System.out.println("Invalid gender input. Defaulting to UNKNOWN.");
                            gender = Gender.OTHER;
                            break;
                    }
                    Groups targetGroup = userDatabase.getGroupByName(groupName3);
                    if (targetGroup != null) {
                        Students newStudent = new Students(targetGroup.getCurrentStudentID(), firstName, lastName, email, password, gender);
                        userDatabase.addStudentToGroup(groupName3, newStudent);
                    } else {
                        System.out.println("Error: Group with name " + groupName3 + " not found");
                    }


                    break;


                case 6:
                    System.out.println("Enter informations to update student:");
                    System.out.println("Enter email:");
                    String email1 = scanner.nextLine();
                    System.out.println("Enter password:");
                    String password1 = scanner.nextLine();
                    System.out.println("Enter first name:");
                    String firstName1 = scanner.nextLine();
                    System.out.println("Enter last name:");
                    String lastName1 = scanner.nextLine();
                    userDatabase.updateStudent(email1, password1, firstName1, lastName1);
                    break;
                case 7:
                    System.out.println("Enter first name to find a student:");
                    System.out.println("Enter first name:");
                    String firstName2 = scanner.nextLine();
                    userDatabase.findStudentByFirstName(firstName2);
                    break;
                case 8:
                    System.out.println("Enter group name to get all students:");
                    System.out.println("Enter group name:");
                    String groupName4 = scanner.nextLine();
                    userDatabase.getAllStudentsByGroupName(groupName4);
                    break;
                case 9:
                    System.out.println("Enter email to get all student's lessons:");
                    String email2 = scanner.nextLine();
                    userDatabase.getAllStudentsLesson(email2);
                    break;
                case 10:
                    System.out.println("Enter email to delete a student:");
                    System.out.println("Enter group name:");
                    String groupName7 = scanner.nextLine();
                    System.out.println("Enter email:");
                    String email3 = scanner.nextLine();
                    userDatabase.deleteStudent(groupName7,email3);
                    break;
                case 11:
                    System.out.println("Enter group name:");
                    String groupName5= scanner.nextLine();
                    System.out.println("Enter lesson name:");
                    String lesson = scanner.nextLine();
                    System.out.println("Enter lessondescription name:");
                    String lessonDescription = scanner.nextLine();
                    Groups targetGroup1 = userDatabase.getGroupByName(groupName5);
                    if (targetGroup1 != null) {
                        Lessons newlesson=new Lessons(targetGroup1.getCurrentLessonID(), lesson,lessonDescription);
                        userDatabase.addNewLessonToGroup(groupName5,newlesson);
                    } else {
                        System.out.println("Error: Group with name " + groupName5 + " not found");
                    };
                    break;

                case 12:
                    System.out.println("Enter lesson name:");
                    String lessonName = scanner.nextLine();
                    userDatabase.getLessonByName(lessonName);
                    break;
                case 13:
                    System.out.println("Enter group name:");
                    String groupName6 = scanner.nextLine();
                    userDatabase.getAllLessonsByGroupName(groupName6);
                    break;

                case 14:
                    System.out.println("Enter group name:");
                    String groupName8 = scanner.nextLine();
                    System.out.println("Enter lesson name:");
                    String lessonName1 = scanner.nextLine();
                    userDatabase.deleteLesson(groupName8,lessonName1);
                    break;
                case 15:
                    System.out.println("Enter group name:");
                    String groupName9 = scanner.nextLine();
                    userDatabase.deleteGroup(groupName9);
                    break;

            }
        }while (true);
    }
}
