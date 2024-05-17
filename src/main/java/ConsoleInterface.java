import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public class ConsoleInterface {
    List<Task> list;

    public ConsoleInterface(DataFileObject data) throws IOException, ParserConfigurationException, SAXException {
        list = data.parseFile("D:\\IdeaProjects\\TaskForisimple\\taskManager.xml");
    }

    void printAllTasks() {
        list.forEach(System.out::println);
    }

    void printTasksInProgress() {
        list.stream().filter(o->o.getState().equals(State.IN_PROGRESS)).forEach(System.out::println);
    }

    void printNewTasks() {
        list.stream().filter(o->o.getState().equals(State.NEW)).forEach(System.out::println);
    }

    void printDoneTasks() {
        list.stream().filter(o->o.getState().equals(State.DONE)).forEach(System.out::println);
    }

}
