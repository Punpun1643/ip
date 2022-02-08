package duke;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

/**
 * Represents deadline related tasks.
 */
public class Deadline extends Task {
    protected LocalDate deadlineDate;
    protected DayOfWeek day;
    protected Month month;
    protected int year;
    protected LocalTime time;
    protected Tag tag;

    /**
     * Constructs a Deadline object.
     *
     * @param description description of the deadline task.
     * @param deadlineDate deadline date.
     * @param time deadline time.
     */
    public Deadline(String description, LocalDate deadlineDate, LocalTime time) {
        super(description);
        this.deadlineDate = deadlineDate;
        this.day = deadlineDate.getDayOfWeek();
        this.month = deadlineDate.getMonth();
        this.year = deadlineDate.getYear();
        this.time = time;
    }

    /**
     * Constructs deadline object with tag.
     *
     * @param description description of deadline task.
     * @param deadlineDate deadline date.
     * @param time deadline time.
     * @param tag deadline tag.
     */
    public Deadline(String description , LocalDate deadlineDate, LocalTime time, Tag tag) {
        super(description);
        this.deadlineDate = deadlineDate;
        this.day = deadlineDate.getDayOfWeek();
        this.month = deadlineDate.getMonth();
        this.year = deadlineDate.getYear();
        this.time = time;
        this.tag = tag;
    }

    /**
     * Tags deadline task.
     *
     * @param taskTag tag for task.
     * @return deadline object.
     */
    @Override
    public Deadline tag(Tag taskTag) {
        return new Deadline(description, deadlineDate, time, taskTag);
    }

    /**
     * Returns a string representation to write to disk.
     *
     * @return
     */
    @Override
    public String toStringForSave() {
        return tag == null
               ? "D " + super.toStringForSave() + " # " + this.deadlineDate + " " + this.time
               : "D " + super.toStringForSave() + " # " + this.deadlineDate + " " + this.time + " " + this.tag.toString();
    }

    /**
     * Returns a string representation of the Deadline object.
     *
     * @return string representation of Deadline object.
     */
    @Override
    public String toString() {
        return tag == null
                ? ("[D]" + super.toString() + " (by: "
                + this.month + " "
                + this.day + " "
                + this.year + " "
                + this.time + ")"
                + "\n")
                : ("[D]" + super.toString() + " (by: "
                + this.month + " "
                + this.day + " "
                + this.year + " "
                + this.time + ")"
                + this.tag.toString()
                + "\n");
    }
}
