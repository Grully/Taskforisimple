import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Scanner;


public class Main {
    static String run() throws IOException, ParserConfigurationException, SAXException {
        Scanner sc = new Scanner(System.in);
        ConsoleInterface consoleInterface = new ConsoleInterface(new TodolistDTOXML());
        TaskCRUDmethods taskCRUDmethods = new TaskCRUDXML(new TodolistDTOXML());
        int id = 0;
        switch (sc.nextLine()) {
            case "help":
                System.out.println("Вывод задач:\n" +
                        "всех: list\n" +
                        "новых: list -s new\n" +
                        "в работе: list -s in_progress\n" +
                        "выполненных: list -s done\n" +
                        "Пометить задачу как выполненную: complete \"идентификатор\"\n" +
                        "Добавить новую задачу: new\n" +
                        "Редактировать: edit \"идентификатор\"\n" +
                        "Удалить: remove \"идентификатор\"\n" +
                        "Выйти из программы: exit");
                break;
            case "list":
                consoleInterface.printAllTasks();
                break;
            case "list -s new":
                consoleInterface.printNewTasks();
                break;
            case "list -s in_progress":
                consoleInterface.printTasksInProgress();
                break;
            case "list -s done":
                consoleInterface.printDoneTasks();
                break;
            case "new":
                taskCRUDmethods.createTask();
                break;
            case "edit":
                System.out.println("Enter id: ");
                id = Integer.parseInt(sc.nextLine());
                taskCRUDmethods.changeTask(id);
                break;
            case "remove":
                System.out.println("Enter id: ");
                id = Integer.parseInt(sc.nextLine());
                taskCRUDmethods.deleteTask(id);
                break;
            case "complete":
                System.out.println("Enter id: ");
                id = Integer.parseInt(sc.nextLine());
                taskCRUDmethods.setToDone(id);
                break;
            case "exit":
                return "exit";

        }
        return "";

    }


    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
        System.out.println("Hello. Print any command. To show all commands, type \"Help\"");
        while (true)
        {
            if(run().equals("exit")) return;

        }
    }
}
