import java.util.ArrayList;
import java.util.Date;

public class Task {

    public  String item;
    public  Date dueDate;
    public  String taskDescription;
    public  String status;



    public Task(String item, String taskDescription, Date dueDate, String status) {

        this.item = item;
        this.dueDate = dueDate;
        this.taskDescription = taskDescription;
        this.status = status;
    }



    public void setTask(String item) { this.item = item;}
    public String getTask() {
        return item;
    }

    public void setDueDate(Date dueDate) { this.dueDate = dueDate; }
    public Date getDueDate() {
        return dueDate;
    }


    public void setDescription(String taskDescription) { this.taskDescription= taskDescription; }
    public String getDescription() {
     return taskDescription;
    }

    public  void setStatus(String status){
        this.status = status;
    }
    public  String getStatus(){
        return status;
    }

    //public void createTask(Task task, String item, String taskDescription){return;}




}

