import java.util.ArrayList;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;
import java.util.InputMismatchException;
import java.util.Iterator;


public class TaskManager {


    private Scanner scanner = new Scanner(System.in);
    ArrayList<Task>  tasks = new ArrayList<>();
    //Task newTask = new Task();
    Date dueDate;

    public void welcomeMessage() {
        System.out.println("Welcome to the To Do List Application");
    }

    public void printInstructions() {
        System.out.println(" - To see the instructions  \n " +
                "- Press 0, To print the list of tasks press 1 \n " +
                "- Press 2, To create a task  \n " +
                "- Press 3, To modify a task  \n " +
                "- Press 4, To remove a task  \n " +
                "- Press 5, To quit  ");
    }




    public void addTask() {
        System.out.println("Please enter a task name: ");
        String item = scanner.nextLine();

        System.out.println("Please enter description for the task: ");
        String taskDescription = scanner.nextLine();

        System.out.println("Please enter the due date for the task in format MM-dd-yyyy: ");
        String date= scanner.nextLine();
        SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy");
        try {
            this.dueDate = format.parse(date);
        } catch (ParseException e) {
            System.out.println("Parse Exception");
        }
        System.out.println("Please enter the status of the task:Finished/Unfinished");
        String status =scanner.nextLine();


        Task.Level statusCheck = Task.Level.Finished;
        switch(statusCheck) {
            case Finished:

                System.out.print("Finished Task");
                break;
            case Unfinished:
                System.out.print("Unfinished Task");
                break;
        }


/*
            try {
                System.out.print("Is the task already done? true/false");
                 status = scanner.nextBoolean();
                if (status == true) {
                    Task.getStatus(Done);
                } else if (status == false) {
                    System.out.println("Task undone");
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input!");
            }

*/


        tasks.add(new Task(item,taskDescription,dueDate,status));
         //   Task.getTask(item);
      // newTask.getDescription(taskDescription);
    }

    public void removeTask() {
        System.out.println("Please type in the name of the tax you want to remove");
        String taskToRemove = scanner.nextLine();

       /* if (tasks.contains(taskToRemove)) {
            int index = tasks.indexOf(taskToRemove);
            //  String number = tasks.get(index);
            tasks.remove(tasks.indexOf(taskToRemove));
            System.out.println(tasks.indexOf(taskToRemove));

        }*/
        Iterator<Task> it = tasks.iterator();
        while(it.hasNext()){
            Task s = it.next();
            if(taskToRemove.equals(s.getTask())){
                it.remove();
            }
        }
    }


    public void modifyTask() {
        System.out.println("Please enter the task name you want to replace");

        String taskToModify = scanner.nextLine();

        System.out.println("Enter the new task :");
        //addTask();
        //tasks.set(tasks.indexOf(taskToModify), new Task());

    }


    public void printArray() {
       // for (int i = 0; i < tasks.size(); i++) {
         //  System.out.println(tasks.get(i));
        tasks.forEach(Task -> {
                System.out.println("Task : " +  Task.getTask() + ", Description : " + Task.getDescription()
                        + ", Due Date : " + Task.getDueDate()+ ", Status : " + Task.getStatus() );
            });

    }


//+ Task.getStatus()

    public void TaskManager() {

        boolean quit = false;
        int choice = 0;
        welcomeMessage();
        printInstructions();
        while (!quit) {
            System.out.println("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 0:
                    printInstructions();
                    break;
                case 1:
                    printArray();
                    break;
                case 2:
                    addTask();

                    break;
                case 3:
                    modifyTask();
                    break;
                case 4:
                    removeTask();
                    break;
                case 5:
                    quit = true;
                    break;
            }

        }

    }
}
