import java.time.LocalDate;
import java.util.Date;

public class Task {
    int id;
    String caption;
    String description;
    int priority;
    State state;
    LocalDate deadline;
    LocalDate complete;

    public Task(int id, String caption, String description, int priority, State state, LocalDate deadline) {
        this.id = id;
        this.caption = caption;
        this.description = description;
        this.priority = priority;
        this.state = state;
        this.deadline = deadline;
        this.complete = null;
    }

    public Task(int id, String caption, String description, int priority, State state, LocalDate date, LocalDate complete) {
        this.id = id;
        this.caption = caption;
        this.description = description;
        this.priority = priority;
        this.state = state;
        this.deadline = date;
        this.complete = complete;
    }

    public LocalDate getComplete() {
        return complete;
    }

    public void setComplete(LocalDate complete) {
        this.complete = complete;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        String str = "";
        switch (state) {
            case NEW -> str = "new";
            case IN_PROGRESS -> str = "in_progress";
            case DONE -> str = "done";
        }

        if (state == State.DONE) {
            return "<Task id=\"" + id + "\" caption=\"" + caption + "\">\n" +
                    "            <Description>" + description + "</Description>\n" +
                    "            <Priority>" + priority + "</Priority>\n" +
                    "            <Deadline>" + deadline + "</Deadline>\n" +
                    "            <Status>" + str + "</Status>\n" +
                    "            <Complete>" + complete + "</Complete>\n" +
                    "</Task>\n";
        } else {
            return "<Task id=\"" + id + "\" caption=\"" + caption + "\">\n" +
                    "            <Description>" + description + "</Description>\n" +
                    "            <Priority>" + priority + "</Priority>\n" +
                    "            <Deadline>" + deadline + "</Deadline>\n" +
                    "            <Status>" + str + "</Status>\n" +

                    "</Task>\n";
        }
    }
}
