import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {


    // initializing saver to save todoList tasks to relativePath
    String fileSeparator = System.getProperty("file.separator");
    String relativePath = "data" + fileSeparator + "duke.txt";


    public Storage() throws IOException {

        File f = new File(this.relativePath);
        // if file does not exist, create file based on filepath
        if (!f.exists()) {
            // create data directory
            Files.createDirectories(Path.of(f.getParent()));

            // create new file
            if (f.createNewFile()) {
                System.out.println(this.relativePath + " file created in the project root directory");
            }
        } else {
            System.out.println(this.relativePath + " already exists in the project root directory");
        }
    }

    public void save(ArrayList<Task> todoList) throws IOException {
        FileWriter fw = new FileWriter(this.relativePath);
        for (int i = 0; i < todoList.size(); i++) {
            fw.write(todoList.get(i).toStringForSave() + "\n");
        }
        fw.close();
    }

    public ArrayList<Task> load() throws FileNotFoundException {
        File f = new File(this.relativePath);
        Scanner s = new Scanner(f);
        ArrayList<Task> todoListTasks = new ArrayList<>();

        // load task from duke.txt to todoList
        while (s.hasNext()) {
            String task = s.nextLine();
            String[] taskContent = task.split("\\\\| ");
            String taskType = taskContent[0];
            String taskStatus = taskContent[2];
            String taskDescription = taskContent[4];


            switch (taskType) {
            case "T":
                Todo todoTask = new Todo(taskDescription);

                // check if task is completed
                if (taskStatus.equals("0")) {
                    todoTask.markAsNotDone();
                } else {
                    todoTask.markAsDone();
                }

                // add todoTask to todoList
                todoListTasks.add(todoTask);
                break;
            case "D":
                LocalTime deadlineTime = LocalTime.parse(taskContent[7]);
                LocalDate deadlineDate = LocalDate.parse(taskContent[6]);

                Deadline deadlineTask = new Deadline(taskDescription, deadlineDate, deadlineTime);

                // check if task is completed
                if (taskStatus.equals("0")) {
                    deadlineTask.markAsNotDone();
                } else {
                    deadlineTask.markAsDone();
                }

                // add deadlineTask to todoList
                todoListTasks.add(deadlineTask);
                break;
            case "E":
                LocalTime eventTime = LocalTime.parse(taskContent[7]);
                LocalDate eventDate = LocalDate.parse(taskContent[6]);

                Event eventTask = new Event(taskDescription, eventDate, eventTime);

                // check if task is completed
                if (taskStatus.equals("0")) {
                    eventTask.markAsNotDone();
                } else {
                    eventTask.markAsDone();
                }

                // add eventTask to todoList
                todoListTasks.add(eventTask);
                break;
            }
        }
        return todoListTasks;
    }
}