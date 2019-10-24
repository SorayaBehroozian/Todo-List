import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static java.util.Arrays.asList;


class TaskManagerTest {

    TaskManager manager = new TaskManager();
    String date = "12/11/2019";

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
    LocalDate dueDate = LocalDate.parse(date,formatter);
    Iterator<Task> it = manager.tasks.iterator();
    @Test
    void welcomeMessage() {
        Assert.assertEquals("Hello", "manager.welcomeMessage()");

    }


    @Test
    void testingTheArrayOfTasks() {
        //   Assert.assertNotNull(newTask);
        ArrayList<Task> testtasks = new ArrayList<>();

        Task taskT = new Task("PiD Exercise", " study csv", dueDate, "Done");
        testtasks.add(taskT);
        List<String> exampleToFail = asList("one", "two", "three");
        Assert.assertTrue(testtasks.equals(exampleToFail));

    }

    @Test
    void addTaskToListTest() {
        for(int i = 0; i < 10; i++) {
            manager.tasks.add(new Task("PiD Exercise", " study csv", dueDate, "Done"));
        }

        Assert.assertEquals(manager.tasks.size(),manager.getListSize());

    }



    @Test
    void testMarkAsDone() {
        while (it.hasNext()) {
            Task s = it.next();
            Task toDo =new Task("Exercise", " study csv", dueDate, "Undone");
            manager.tasks.add(new Task("Exercise", " study csv", dueDate, "Undone"));
            String taskName= "Exercise";
            if (taskName.equals(s.getTask())) {
            }

            if (taskName.equals(s.getTask())) {
               this.manager.status = "Done";
            }
            Assert.assertEquals("Done",toDo.getStatus());
        }
    }

    @Test
    void testRemoveTask(){
        String taskToRemove ="PiD Exercise";
        while(it.hasNext()){
            Task s = it.next();
            if(taskToRemove.equals(s.getTask())){
                it.remove();
            }
            Assert.assertEquals(1,manager.getListSize());
        }
    }



}