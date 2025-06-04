import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private List<Task> tasks;
    private int nextId = 1;
    private final String filePath;

    public TaskManager(String filePath) {
        this.filePath = filePath;
        tasks = new ArrayList<>();
        loadTasksFromFile();
    }

    public void addTask(String title, String priority, LocalDate dueDate) {
        Task t = new Task(nextId++, title, priority, dueDate, false);
        tasks.add(t);
    }

    public void removeTask(int id) {
        tasks.removeIf(t -> t.getId() == id);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public List<Task> filterTasksByPriority(String priority) {
        List<Task> filtered = new ArrayList<>();
        for (Task t : tasks) {
            if (t.getPriority().equalsIgnoreCase(priority)) {
                filtered.add(t);
            }
        }
        return filtered;
    }

    public Task getTaskById(int id) {
        for (Task t : tasks) {
            if (t.getId() == id) return t;
        }
        return null;
    }

    public void markTaskDone(int id) {
        Task t = getTaskById(id);
        if (t != null) t.setDone(true);
    }

    public void saveTasksToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Task t : tasks) {
                writer.write(t.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Fehler beim Speichern: " + e.getMessage());
        }
    }

    public void loadTasksFromFile() {
        tasks.clear();
        File file = new File(filePath);
        if (!file.exists()) return;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int maxId = 0;
            while ((line = reader.readLine()) != null) {
                Task t = Task.fromFileString(line);
                tasks.add(t);
                if (t.getId() > maxId) maxId = t.getId();
            }
            nextId = maxId + 1;
        } catch (IOException e) {
            System.out.println("Fehler beim Laden: " + e.getMessage());
        }
    }
}
