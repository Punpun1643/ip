package duke;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toStringForSave() {
        return "T "+ super.toStringForSave();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
