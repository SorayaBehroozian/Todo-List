import java.util.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.stream.Collectors;
import java.io.FileWriter;
import java.util.Date;
import java.util.stream.Stream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;



public class TaskManager {


    private Scanner scanner = new Scanner(System.in);
    ArrayList<Task>  tasks = new ArrayList<>();

  //  Date dueDate;
    String status;

    //This method displays Welcome Message.
    public void welcomeMessage() {
        System.out.println("Welcome to the To Do List Application");
    }


// Print Instructions
        public void printInstructions() {
            System.out.println("" +
                    " - Press 1, Show task list\n" +
                    " - Press 2, To add a new task\n" +
                    " - Press 3, To edit a task\n" +
                    " - Press 4, To save and quit\n" );
        }






    public void addTask() throws ParseException{

//Adding a task name by the user.
        System.out.println("Please enter a task name: ");
        String item = scanner.nextLine();
// Adding project description
        System.out.println("Please enter description for the task: ");
        String taskDescription = scanner.nextLine();

//Getting the date in a weird range, needs to be restricted
        System.out.println("Please enter the due date for the task in format MM-dd-yyyy: ");
        String date= scanner.nextLine();
        Date dueDate = new SimpleDateFormat("MM-dd-yyyy").parse(date);

//Marking the status of the task and printing an error if one of the two options is not the input.
        System.out.println("Please enter the status of the task:Finished/Unfinished");
        String statusCheck =scanner.nextLine();

        try { if (statusCheck.equals("Finished")){
             this.status = "Finished";
        }
        else if (statusCheck.equals("Unfinished")){
             this.status = "Unfinished";
        }
        else { System.out.println("Invalid input!");
        }
        }catch (InputMismatchException e) {
            System.out.println("Invalid input!");
        }

//Creating the objact in class Task and adding it to the ArrayList.
        Task newTask = new Task(item,taskDescription,dueDate,status);
        tasks.add(newTask);
    }


//This method removes a task that user has input the title of by searching through the tasks and finding a match. Should I
//add another criteria to avoid deleting the wrong task? in case of duplicates.
    public void removeTask() {
        System.out.println("Please type in the name of the tax you want to remove");
        String taskToRemove = scanner.nextLine();

        Iterator<Task> it = tasks.iterator();

        while(it.hasNext()){
            Task s = it.next();
            if(taskToRemove.equals(s.getTask())){
                it.remove();
            }
        }
    }

//This method is there to modify the task. All the parameters could be changed.If the user leaves input blank,
// the value will be the same as it was.
    public void modifyTask() throws ParseException {
        System.out.println("Please enter the task name you want to replace");
        String taskToModify = scanner.nextLine();

        Iterator<Task> it = tasks.iterator();

        while (it.hasNext()) {
            Task s = it.next();
            if (taskToModify.equals(s.getTask())) {
            }
            System.out.println("Enter the new task name :");
            String newName = scanner.nextLine();
            if (newName == null) newName.equals(s.getTask());
            System.out.println("Please enter new description for the task: ");
            String newTaskDescription = scanner.nextLine();
            if (newTaskDescription == null) newTaskDescription.equals(s.getDescription());
            System.out.println("Please enter the due date for the task in format MM-dd-yyyy: ");
            String newDate = scanner.nextLine();
            Date newDueDate = new SimpleDateFormat("MM-dd-yyyy").parse(newDate);
            if (newDate == null) newDueDate.equals(s.getDueDate());
            System.out.println("Please enter the status of the task:Finished/Unfinished");
            String newStatusCheck = scanner.nextLine();
            if (newStatusCheck == null) status.equals(s.getStatus());
            try {
                if (newStatusCheck.equals("Finished")) {
                    this.status = "Finished";
                } else if (newStatusCheck.equals("Unfinished")) {
                    this.status = "Unfinished";
                } else {
                    System.out.println("Invalid input!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input!");
            }
            Task modifiedTask = new Task(newName, newTaskDescription, newDueDate, status);
            tasks.set(tasks.indexOf(taskToModify), modifiedTask);

        }
    }

// This method sorts the tasks by their name.
    public void sortTasksByName(){
        List<Task> sortedByTask = tasks.stream().sorted((t1, t2)  ->  t1.getTask().compareTo(t2.getTask())).collect(Collectors.toList());
        sortedByTask.forEach(Task -> {
           System.out.println("Task : " +  Task.getTask() + ", Description : " + Task.getDescription()
            + ", Due Date : " + Task.getDueDate()+ ", Status : " + Task.getStatus() );
        });

        
    }

//THis method sorts the tasks by their due date.
    public void sortTasksByDueDate(){
        List<Task> sortedByTask = tasks.stream().sorted((t1, t2)  ->  t1.getDueDate().compareTo(t2.getDueDate())).collect(Collectors.toList());
        sortedByTask.forEach(Task -> {
           System.out.println(", Due Date : " + Task.getDueDate() + "Task : " +  Task.getTask()
                   + ", Description : " + Task.getDescription() + ", Status : " + Task.getStatus() );
        });
    }


// This method saves the tasks in a file with name entered by the user.

   public void save() throws FileNotFoundException {
       try {
           System.out.println("Enter name of the file :");
           String fileName = scanner.nextLine();

           FileOutputStream fout = new FileOutputStream(fileName);
           ObjectOutputStream oos = new ObjectOutputStream(fout);
           oos.writeObject(tasks);
           fout.close();
       }catch(IOException ioException) {
            ioException.printStackTrace();
       }
   }

//This method counts the number of finished and unfinished tasks.
    public void statusCount(){
        // Task taskStream = tasks.stream()
       long countFinished =  tasks.stream().filter(x -> "Finished".equals(x.getStatus())).count();
       long countUnfinished = tasks.size() - countFinished;
       System.out.println("You have "+ countFinished + " tasks done and " + countUnfinished + " tasks undone!");
    }

    
// This method gives the choice to either update or remove a task by bringing two methods under a switch.
       public void updateTask(){
        boolean quit = false;
        int choice = 0;
          while (!quit) {
              System.out.println("Enter 1 to update, 2 to remove a task and 3 to return to main menue: ");
              choice = scanner.nextInt();
              scanner.nextLine();
              switch (choice) {
                  case 1:
                      try {
                          sortTasksByName();
                          modifyTask();
                      } catch (ParseException e) {
                          e.printStackTrace();
                      }
                      break;
                  case 2:
                      sortTasksByName();
                      removeTask();
                  case 3:
                      quit = true;
                      printInstructions();

              }
          }
       }

//This method gives the choice wether to print tasks sorted by name or by due date, using a switch.
    public void showTaskList() {
       boolean quit = false;
       int choice = 0;
         while (!quit) {
             System.out.println("Enter 1 to sort by name, 2 to sort by due date and 3 to return to main menue: ");
             choice = scanner.nextInt();
             scanner.nextLine();
             switch (choice) {
                 case 1:
                     sortTasksByName();
                     break;
                 case 2:
                     sortTasksByDueDate();
                 case 3:
                     quit = true;
                     printInstructions();
             }
         }
    }





//The main switch, user can choose 4 actions.
    public void TaskManager() throws ParseException {

        boolean quit = false;
        int choice = 1;
        welcomeMessage();
        printInstructions();
        while (!quit) {
            statusCount();
            System.out.println("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    showTaskList();
                    break;

                case 2:
                    addTask();
                    break;

                case 3:
                    updateTask();
                    break;

                case 4:
                    try {
                        save();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    quit = true;
                    break;
            }

        }

    }
}
