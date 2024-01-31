package lmsTask;

import java.util.ArrayList;
import java.util.List;


public class Groups {
    private int currentStudentID = 1;
    private int currentLessonID = 1;
    private  int id;
    private String groupName;
    private String description;
    private List<Lessons>lessons;
    private  List<Students>students;

    public Groups(String groupName, String description, List<Lessons> lessons, List<Students> students) {
        this.groupName = groupName;
        this.description = description;
        this.lessons = lessons;
        this.students = students;
    }

    public int getCurrentStudentID() {
        return currentStudentID++;
    }

    public int getCurrentLessonID() {
        return currentLessonID++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Lessons> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lessons> lessons) {
        this.lessons = lessons;
    }

    public List<Students> getStudents() {
        return students;
    }

    public void setStudents(List<Students> students) {
        this.students = students;
    }

    public  void addStudent(Students student){
        if (students == null) {
            students = new ArrayList<>();
        }
        students.add(student);
    }

    public void addLesson(Lessons lesson){
        if (lesson == null){
            lessons=new ArrayList<>();
        }else {
            lessons.add(lesson);
        }
    }

    @Override
    public String toString() {
        return "Groups{" +
                "id=" + id +
                ", groupName='" + groupName + '\'' +
                ", description='" + description + '\'' +
                ", lessons=" + lessons +
                ", students=" + students +
                '}';
    }

}
