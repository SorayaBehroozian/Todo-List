import java.util.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.stream.Collectors;
import java.io.FileWriter;
import java.util.Date;
import java.util.stream.Stream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**
 *This class contains all the main methods used to manage a task.
 */


public class TaskManager {

    //Since the code is ever changing, it is important to use a static serialVersionUID in order to avoid confusion
    // while reading/writing the file.
    private static final long serialVersionUID = 4L;
    private Scanner scanner = new Scanner(System.in);
    ArrayList<Task>  tasks = new ArrayList<>();
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


    /**
     * addTask reads the parameters, creates and object of type Task and puts it in an ArrayList.
      */

    public void addTask(){

    //Adding a task name by the user.
        System.out.println("Please enter a task name: ");
        String item = scanner.nextLine();
    // Adding project description
        System.out.println("Please enter description for the task: ");
        String taskDescription = scanner.nextLine();

    //Getting the date in a weird way rn, it does not parse.
        System.out.println("Please enter the due date for the task in format dd/mm/yyyy: ");
        String date= scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        LocalDate dueDate = LocalDate.parse(date,formatter);

    //Marking the status of the task and printing an error if one of the two options is not the input.
        System.out.println("Please enter the status of the task:Done/Undone");
        String statusCheck =scanner.nextLine();

        try { if (statusCheck.equals("Done")){
             this.status = "Done";
        }
        else if (statusCheck.equals("Undone")){
             this.status = "Undone";
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


    /**
     * This method removes a task that user has input the title of by searching through the tasks and finding a match.
     * Should I add another criteria to avoid deleting the wrong task? in case of duplicates.
     */

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


    /**
     * Marking a task as done
      * @throws ParseException
     */

    public void markAsDone(){
        System.out.println("Please enter the task name you want to replace");
        String taskIsDone = scanner.nextLine();

        Iterator<Task> it = tasks.iterator();

        while (it.hasNext()) {
            Task s = it.next();
            if (taskIsDone.equals(s.getTask())) {
            }

            if (taskIsDone.equals(s.getTask())) {
                this.status = "Done";
            }
        }
    }

    /**
     * This method is there to modify the task. All the parameters could be changed.If the user leaves input blank,
     * // the value will be the same as it was.
     * @throws ParseException
     */

    public void modifyTask() throws ParseException {
        System.out.println("Please enter the task name you want to replace");
        String taskToModify = scanner.nextLine();

        Iterator<Task> it = tasks.iterator();

        while (it.hasNext()) {
            Task s = it.next();
            if (taskToModify.equals(s.getTask())) {
            }
            //getting the name
            System.out.println("Enter the new task name :");
            String newName = scanner.nextLine();
            if (newName == null) {newName.equals(s.getTask());}
            
            //getting the project
            System.out.println("Please enter new description for the task: ");
            String newTaskDescription = scanner.nextLine();
            if (newTaskDescription == null){ newTaskDescription.equals(s.getDescription());}
            
            //getting the due date
            System.out.println("Please enter the new due date for the task in format MM-dd-yyyy: ");
            String newDate = scanner.nextLine();
           // String date= scanner.nextLine();
            LocalDate newDueDate = null;
            if (newDate == null) {newDueDate.equals(s.getDueDate());} 
            else {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
                LocalDate NewDueDate = LocalDate.parse(newDate, formatter);
            }

           //Getting the new status
            System.out.println("Please enter the status of the task:Done/Undone");
            String newStatusCheck = scanner.nextLine();
            if (newStatusCheck == null){ status.equals(s.getStatus());}
            try {
                if (newStatusCheck.equals("Done")) {
                    this.status = "Done";
                } else if (newStatusCheck.equals("Undone")) {
                    this.status = "Undone";
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



    /**
     *  This method sorts the tasks by their name.
     */

    public void sortTasksByName(){
        List<Task> sortedByTask = tasks.stream().sorted((t1, t2)  ->  t1.getTask().compareTo(t2.getTask())).collect(Collectors.toList());
        sortedByTask.forEach(Task -> {
           System.out.println("Task : " +  Task.getTask() + ", Description : " + Task.getDescription()
            + ", Due Date : " + Task.getDueDate()+ ", Status : " + Task.getStatus() );
        });
    }

    /**
     *  This method sorts the tasks by their due date.
     */
    public void sortTasksByDueDate(){
        List<Task> sortedByTask = tasks.stream().sorted((t1, t2)  ->  t1.getDueDate().compareTo(t2.getDueDate())).collect(Collectors.toList());
        sortedByTask.forEach(Task -> {
           System.out.println(", Due Date : " + Task.getDueDate() + "Task : " +  Task.getTask()
                   + ", Description : " + Task.getDescription() + ", Status : " + Task.getStatus() );
        });
    }


    /**
     * This method saves the tasks in a file with name entered by the user.
     */

   public void save() throws FileNotFoundException {
       try {
           System.out.println("Enter name of the file :");
           String fileName = scanner.nextLine();

           FileOutputStream fout = new FileOutputStream(fileName);
           ObjectOutputStream oos = new ObjectOutputStream(fout);
           oos.writeObject(tasks);
           oos.close();
           fout.close();
       } catch (IOException ioException) {
           ioException.printStackTrace();
       }
   }

    /**
     * This method reads the file containing the tasks.
     */
       public void read () {
       try {
           FileInputStream fis = new FileInputStream("kn");
           ObjectInputStream ois = new ObjectInputStream(fis);

           this.tasks = (ArrayList<Task>) ois.readObject();
           ois.close();
           fis.close();
       }catch (IOException e) {
           e.printStackTrace();
       } catch (ClassNotFoundException e) {
           e.printStackTrace();
       }
   }

    /**
     * This method counts the number of Done and Undone tasks.
     */

    public void statusCount(){
        // Task taskStream = tasks.stream()
       long countDone =  tasks.stream().filter(x -> "Done".equals(x.getStatus())).count();
       long countUndone = tasks.size() - countDone;
       System.out.println("You have "+ countDone + " tasks done and " + countUndone + " tasks undone!");
    }


    /**
     *  This method gives the choice to either update, remove or mark a task as done a task by bringing two methods
     *  under a switch. Basically calling the methods created above.
     */
       public void updateTask(){
        boolean quit = false;
        int choice = 0;
          while (!quit) {
              System.out.println("Enter 1 to update, 2 to remove a task, 3 to mark a task as done and 4 to return to main menue: ");
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
                      markAsDone();
                  case 4:
                      quit = true;
                      printInstructions();

              }
          }
       }


    /**
     * This method gives the choice wether to print tasks sorted by name or by due date, using a switch.
     * // Basically calling the methods created above.
      */

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


    /**
     * The main switch, user can choose 4 actions.
     * @throws ParseException
     */


    public void TaskManager() throws ParseException {

        boolean quit = false;
        int choice = 1;
        read();
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
