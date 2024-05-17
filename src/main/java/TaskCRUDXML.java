import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class TaskCRUDXML implements TaskCRUDmethods {

    List<Task> taskList;

    public TaskCRUDXML(DataFileObject data) throws IOException, ParserConfigurationException, SAXException {
        taskList = data.parseFile("D:\\IdeaProjects\\TaskForisimple\\taskManager.xml");
    }

    @Override
    public void savetoFile(String path) throws IOException {
        try (FileWriter writer = new FileWriter(path)) {
            writer.write("<ToDoList>\n");
            for (Task task : taskList) {
                writer.write(task.toString());
            }
            writer.write("</ToDoList>");
        }

    }

    @Override
    public void createTask() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter caption: ");
        String caption;
        while (true) {
            caption = scanner.nextLine();
            if (caption.length() > 50) {
                System.out.println("Too long, try again;");
            } else break;
        }
        System.out.println("Enter description: ");
        String description = scanner.nextLine();
        System.out.println("Enter priority from 0 to 10: ");
        int priority = 0;
        while (true) {
            priority = Integer.parseInt(scanner.nextLine());
            if (priority < 0 || priority > 10) {
                System.out.println("Wrong number, try again;");
            } else break;
        }
        System.out.println("Enter deadline date in format YYYY:MM:DD: ");
        String deadline = scanner.nextLine();
        LocalDate dl = LocalDate.parse(deadline);
        taskList.add(new Task(taskList.size() + 1, caption, description, priority, State.NEW, dl));
        savetoFile("D:\\IdeaProjects\\TaskForisimple\\taskManager.xml");
    }

    @Override
    public void changeTask(int id) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter caption or enter skip for not changing: ");
        String caption;
        while (true) {
            caption = scanner.nextLine();
            if (caption.equals("skip")) {
                caption = taskList.get(id - 1).getCaption();
                break;
            }
            if (caption.length() > 50) {
                System.out.println("Too long, try again;");
            } else break;
        }
        System.out.println("Enter description or enter skip for not changing: ");
        String description = scanner.nextLine();
        if (description.equals("skip")) {
            description = taskList.get(id - 1).getDescription();
        }
        System.out.println("Enter priority from 0 to 10 or enter -1 for not changing: ");
        int priority = 0;
        while (true) {

            priority = Integer.parseInt(scanner.nextLine());
            if (priority == -1) {
                priority = taskList.get(id - 1).getPriority();
                break;
            }
            if (priority < 0 || priority > 10) {
                System.out.println("Wrong number, try again;");
            } else break;
        }
        System.out.println("Enter deadline date in format YYYY:MM:DD or enter skip for not changing: ");
        String deadline = scanner.nextLine();
        LocalDate dl;
        if (deadline.equals("skip")) {
            dl = taskList.get(id - 1).deadline;
        } else {
            dl = LocalDate.parse(deadline);
        }
        taskList.set(id - 1, new Task(id, caption, description, priority, State.NEW, dl));
        savetoFile("D:\\IdeaProjects\\TaskForisimple\\taskManager.xml");

    }

    @Override
    public void deleteTask(int id) throws IOException {
        taskList.remove(id - 1);
        for (Task task : taskList) {
            if (task.id > id) {
                task.id -= 1;
            }
        }
        savetoFile("D:\\IdeaProjects\\TaskForisimple\\taskManager.xml");

    }

    @Override
    public void setToDone(int id) throws IOException {
        taskList.get(id - 1).state = State.DONE;
        taskList.get(id - 1).complete = LocalDate.now();
        savetoFile("D:\\IdeaProjects\\TaskForisimple\\taskManager.xml");
    }
}
