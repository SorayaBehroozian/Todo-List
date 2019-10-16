import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.io.Serializable;
public class Task implements Serializable {


    /**
     * Fields
     */
    public  String item;
    public  LocalDate dueDate;
    public  String taskDescription;
    public  String status;

    /**
     * Constructor, objects of this class's type will have the following parameters.
     * @param item
     * @param taskDescription
     * @param dueDate
     * @param status
     */

    public Task(String item, String taskDescription, LocalDate dueDate, String status) {

        this.item = item;
        this.dueDate = dueDate;
        this.taskDescription = taskDescription;
        this.status = status;
    }

    /**
     * set and get methods for each parameter.
     * @param item
     */

    public void setTask(String item) { this.item = item;}
    public String getTask() {
        return item;
    }

    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }
    public LocalDate getDueDate() {
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

