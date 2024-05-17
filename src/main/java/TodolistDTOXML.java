import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TodolistDTOXML implements DataFileObject {
    @Override
    public List<Task> parseFile(String path) throws IOException, ParserConfigurationException, SAXException {
        List<Task> result = new ArrayList<>();
        int id;
        String caption;
        String description;
        int priority;
        State state = null;
        LocalDate deadline;
        LocalDate complete;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File(path));
        NodeList taskList = document.getDocumentElement().getElementsByTagName("Task");
        for (int i = 0; i < taskList.getLength(); i++) {
            id = Integer.parseInt(taskList.item(i).getAttributes().getNamedItem("id").getNodeValue());
            caption = taskList.item(i).getAttributes().getNamedItem("caption").getTextContent();
            description = taskList.item(i).getChildNodes().item(1).getFirstChild().getNodeValue();
            priority = Integer.parseInt(taskList.item(i).getChildNodes().item(3).getFirstChild().getNodeValue());
            deadline = LocalDate.parse(taskList.item(i).getChildNodes().item(5).getFirstChild().getNodeValue());
            String readState = taskList.item(i).getChildNodes().item(7).getFirstChild().getNodeValue();
            state = switch (readState) {
                case "new" -> State.NEW;
                case "in_progress" -> State.IN_PROGRESS;
                case "done" -> State.DONE;
                default -> state;
            };
            if (state == State.DONE) {
                complete = LocalDate.parse(taskList.item(i).getChildNodes().item(9).getFirstChild().getNodeValue());
                result.add(new Task(id, caption, description, priority, state, deadline, complete));
            } else {
                result.add(new Task(id, caption, description, priority, state, deadline));
            }
        }

        return result;
    }
}
