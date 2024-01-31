package lmsTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserDatabase {
    private static int currentGroupID=1;
    private static int currentID=1;

    private List<User>users;
    private List<Groups>groups;
    private List<Students>students;
    private List<Lessons>lessons;

    public UserDatabase() {
        this.users=new ArrayList<>();
        this.groups=new ArrayList<>();
        this.students=new ArrayList<>();
        addUser(new User("Rukuia","Kaarbekovna","admin@gmail.com","1234",Gender.FEMALE));
        addUser(new User("Ali","Bekov","student@gmail.com","1111",Gender.MALE));

    }
//Student methods

    public void addStudentToGroup(String groupName, Students student) {
        Groups group = getGroupByName(groupName);
        if (group != null) {
            student.setId(group.getCurrentStudentID());
            group.addStudent(student);
            System.out.println("Student " + student.getFirstName() + " " + student.getLastName() +
                    " added to group " + groupName);
        } else {
            System.out.println("Error: Group with name " + groupName + " not found");
        }
    }


public void updateStudent(String email,String password,String newFirstName,String newLastNmae){
      for (Groups groups1:groups){
          for (Students student:groups1.getStudents()){
              if (student.getEmail().equals(email) && student.getPassword().equals(password)){
                  student.setFirstName(newFirstName);
                  student.setLastName(newLastNmae);
                  System.out.println(student+ " is successfully updated");
                  return;
              }else {
                  System.out.println("error email or password");
              }
          }
      }

    }

    public void findStudentByFirstName(String firstName){
        for (Groups groups1:groups){
            for (Students student:groups1.getStudents()){
                if (student.getFirstName().equals(firstName)){
                    System.out.println(firstName+ "  has founded: "+student);
                    return;
                }else {
                    System.out.println("error firstName(not found)");
                }
            }
        }

    }

    public void getAllStudentsByGroupName(String groupName){
        for (Groups groups1:groups){
          if (groups1.getGroupName().equals(groupName)){
              System.out.println("All students in the group: "+groupName +": "+ groups1.getStudents());
              return;
          }else{
              System.out.println("Error group name");
          }
        }
    }

    public void getAllStudentsLesson(String email) {
        for (Groups groups1 : groups) {
            for (Students students1 : groups1.getStudents()) {
                if (students1.getEmail().equals(email)) {
                    List<Lessons> lessons = groups1.getLessons();
                    if (lessons != null) {
                        System.out.println("All student's lessons in the group by " + email + ": " + lessons);
                    } else {
                        System.out.println("No lessons found for " + email);
                    }
                    return;
                }
            }
        }
        System.out.println("Error: Student with email " + email + " not found");
    }

    public void deleteStudent(String groupName, String email) {
        Groups group = getGroupByName(groupName);
        if (group != null) {
            List<Students> students1 = group.getStudents();
            if (students1 != null) {
                Students studentToRemove = null;
                for (Students student : students1) {
                    if (student.getEmail().equals(email)) {
                        studentToRemove = student;
                        break;
                    }
                }
                if (studentToRemove != null) {
                    students1.remove(studentToRemove);
                    System.out.println("Student with email " + email + " is successfully deleted from group " + groupName);
                } else {
                    System.out.println("Error: Student with email " + email + " not found in group " + groupName);
                }
            } else {
                System.out.println("Error: Students not found in group " + groupName);
            }
        } else {
            System.out.println("Error: Group with name " + groupName + " not found");
        }
    }

    // lesson methods

    public void addNewLessonToGroup(String groupName,Lessons lesson){
      Groups groups1=getGroupByName(groupName);
      if (groups1 !=null){
          groups1.setId(groups1.getCurrentLessonID());
          groups1.addLesson(lesson);
          System.out.println("Lesson " + lesson.getLessonName() + " " + " added to group " + groupName);
      } else {
          System.out.println("Error: Group with name " + groupName + " not found");
      }
    }

    public void getLessonByName(String lessonName){
        for (Groups groups1:groups){
            for (Lessons lesson:groups1.getLessons()){
                if (lesson.getLessonName().equals(lessonName)){
                    System.out.println(lessonName+ "  has founded: "+lesson);
                    return;
                }else {
                    System.out.println("error lesson Name(not found)");
                }
            }
        }

    }
    public void getAllLessonsByGroupName(String groupName){
        for (Groups groups1:groups){
            if (groups1.getGroupName().equals(groupName)){
                System.out.println("All lessons in the group: "+groupName +": "+ groups1.getLessons());
                return;
            }else{
                System.out.println("Error group name");
            }
        }
    }

    public void deleteLesson(String groupName, String lessonName) {
        Groups group = getGroupByName(groupName);
        if (group != null) {
            List<Lessons> lessons = group.getLessons();
            for (Lessons lesson : lessons) {
                if (lesson.getLessonName().equals(lessonName)) {
                    lessons.remove(lesson);
                    System.out.println("Lesson " + lessonName + " is successfully deleted from group " + groupName);
                    return;
                }
            }
            System.out.println("Error: Lesson with name " + lessonName + " not found in group " + groupName);
        } else {
            System.out.println("Error: Group with name " + groupName + " not found");
        }
    }


    // group methods
    public void addNewGroup(Groups group) {
        boolean groupExists = false;
        for (Groups existingGroup : groups) {
            if (existingGroup.getGroupName().equals(group.getGroupName())) {
                groupExists = true;
                break;
            }
        }
        if (groupExists) {
            System.out.println("Group already exists. Please try again.");
        } else {
            group.setId(currentGroupID++);
            groups.add(group);
            System.out.println(group.getGroupName() + " is successfully added");
        }
    }

    public Groups getGroupByName(String groupName) {
        for (Groups existingGroup : groups) {
            if (existingGroup.getGroupName().equals(groupName)) {
                return existingGroup;
            }
        }

        System.out.println(groupName + " group is not found");
        return null;
    }

    public void updateGroupName(String groupName, String newGroupName) {
        Groups groupToUpdate = getGroupByName(groupName);

        if (groupToUpdate != null) {
            groupToUpdate.setGroupName(newGroupName);
            System.out.println("Group name is updated: " + groupToUpdate);
        }
    }
    public List<Groups>  getAllGroups() {
       if (groups!=null){
           return groups;
       }
       return new ArrayList<>();
    }
    public void deleteGroup(String lessonName){
        if (groups.remove(getGroupByName(lessonName))){
            System.out.println(lessonName+" deleted from"+" :"+getAllGroups());
        }else{
            System.out.println(lessonName+" not found from"+" :"+getAllGroups());
        }
        }

// user methods
    public  void addUser(User user){
        user.setId(currentID++);
        users.add(user);
    }
    public boolean userExist(String email,String password){
        for (User user :users){
            if (user.getEmail().equals(email) && user.getPassword().equals(password)){
                System.out.println("Welcome!");
                return true;
            }
        }
        System.out.println("error email or password. Please try again.");
        return false;
    }

    public boolean doesUserEmailExist(String email){
        for (User user :users){
            if (user.getEmail().equals(email)){
                return true;
            }
        }
        return false;
    }
//    public boolean createUser(String email,String password){
//        if(doesUserEmailExist(email)){
//            System.out.println("email already exists. Please choose another one.");
//            return false;
//        }else {
//            users.add(new User(email,password));
//            System.out.println("User created successfully!");
//            return true;
//        }}

    public boolean updateUserPassword(String email, String password){
         for (User user:users){
             if (doesUserEmailExist(email)){
                if (password.length()>=7){
                    user.setPassword(password);
                    System.out.println("Password is succesfully changed:\n" +user);
                    return true;
                }else {
                    System.out.println("Error: Invalid password");
                    return false;
                }
             }
         }
        System.out.println("Error: User with email " + email + " not found");
        return false;
    }

    }
