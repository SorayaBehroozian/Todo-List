import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


class TaskTest {
    String date = "12/11/2019";
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
    LocalDate dueDate = LocalDate.parse(date,formatter);
    Task task = new Task("PiD Exercise", " study csv", dueDate, "Done");
    @BeforeEach
     void setup() throws Exception{
      try {

          Task task = new Task("PiD Exercise", " study csv", dueDate, "Done");
      } catch (Exception e) {
          e.printStackTrace();
      }
    }

    @Test
    void taskNotNull() {
        Assert.assertNotNull("task");
        System.out.println("The task is not empty");
    }

    @Test
    void getDescription() {
           String strexpected = " csv";
           Assert.assertEquals(strexpected, task.getDescription());

    }



    @Test
    void getStatus() {
        String strexpected = " Done";
        Assert.assertEquals(strexpected, task.getStatus());
    }
}