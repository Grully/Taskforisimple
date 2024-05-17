import java.io.IOException;
import java.util.List;

public interface TaskCRUDmethods {
    void createTask() throws IOException;
    void changeTask(int id) throws IOException ;
    void deleteTask(int id) throws IOException ;
    void setToDone(int id) throws IOException ;
    void savetoFile(String path) throws IOException;

}
