import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import static java.util.Arrays.asList;
import static org.testng.Assert.assertTrue;


class TaskManagerTest {

    TaskManager manager = new TaskManager();
    String date = "12/11/2019";
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
    LocalDate dueDate = LocalDate.parse(date,formatter);
    @Test
    void welcomeMessage() {
      Assert.assertEquals("Hello", "manager.welcomeMessage()");

    }

    @Test
    void printInstructions() {
    }

    @Test
    void testingTheArrayOfTasks() {
     //   Assert.assertNotNull(newTask);
        ArrayList<Task> testtasks = new ArrayList<>();

        Task taskT = new Task("PiD Exercise", " study csv", dueDate, "Done");
        testtasks.add(taskT);
        List<String> exampleToFail = asList("one", "two", "three");
        assertTrue(testtasks.equals(exampleToFail));

    }

    @Test
    void addTaskToListTest() {
            for(int i = 0; i < 10; i++) {
                manager.tasks.add(new Task("PiD Exercise", " study csv", dueDate, "Done"));
            }

            Assert.assertEquals(manager.tasks.size(),manager.getListSize());

        }


    @Test
    void markAsDone() {
    }



    @Test
    void save() {
    }

    @Test
    void statusCount() {
    }

    @Test
    void showTaskList() {
    }
}