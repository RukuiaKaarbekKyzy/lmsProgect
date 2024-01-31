package lmsTask;

public class Lessons {
    private int id;
    private String lessonName;
    private String taskDescription;

    public Lessons(int id,String lessonName, String taskDescription) {
        this.id = id;
        this.lessonName = lessonName;
        this.taskDescription = taskDescription;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    @Override
    public String toString() {
        return "Lessons{" +
                "id=" + id +
                ", lessonName='" + lessonName + '\'' +
                ", taskDescription='" + taskDescription + '\'' +
                '}';
    }
}
