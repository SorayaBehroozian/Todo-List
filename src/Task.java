import java.util.ArrayList;
import java.util.Date;

public class Task {

    public  String item;
    public  Date dueDate;
    public  String taskDescription;
    public  String status;


    // public  boolean status;

    public enum Level {
        Finished, Unfinished ;
    }

    public Task(String item, String taskDescription, Date dueDate, String status) {

        this.item = item;
        this.dueDate = dueDate;
        this.taskDescription = taskDescription;
        this.status = status;
    }




    public String getTask() {
        return item;
    }

    public Date getDueDate() {
        return dueDate;
    }



    public String getDescription() {
     return taskDescription;
    }

    public  String getStatus(){
        return status;
    }

    public void createTask(Task task, String item, String taskDescription){
        return;
    }




}

