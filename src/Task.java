import java.time.LocalDate;

public class Task {
    private int id;
    private String title;
    private String priority; 
    private LocalDate dueDate;
    private boolean isDone;

    public Task(int id, String title, String priority, LocalDate dueDate, boolean isDone) {
        this.id = id;
        this.title = title;
        this.priority = priority;
        this.dueDate = dueDate;
        this.isDone = isDone;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getPriority() { return priority; }
    public LocalDate getDueDate() { return dueDate; }
    public boolean isDone() { return isDone; }
    public void setDone(boolean done) { isDone = done; }

    @Override
    public String toString() {
        return String.format("ID:%d | [%s] %s | Fällig: %s | Status: %s",
                id, priority.toUpperCase(), title, dueDate.toString(), isDone ? "Erledigt" : "Offen");
    }

    // Zum Speichern in Datei (CSV-Format)
    public String toFileString() {
        return id + ";" + title + ";" + priority + ";" + dueDate + ";" + isDone;
    }

    public static Task fromFileString(String line) {
        String[] parts = line.split(";");
        return new Task(
                Integer.parseInt(parts[0]),
                parts[1],
                parts[2],
                LocalDate.parse(parts[3]),
                Boolean.parseBoolean(parts[4])
        );
    }
}
