import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface DataFileObject {
    List<Task> parseFile(String path) throws IOException, ParserConfigurationException, SAXException;

}
