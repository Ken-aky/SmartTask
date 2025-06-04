import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager("tasks.txt");
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- SmartTask Manager ---");
            System.out.println("1) Aufgaben anzeigen");
            System.out.println("2) Aufgabe hinzufügen");
            System.out.println("3) Aufgabe löschen");
            System.out.println("4) Aufgaben nach Priorität filtern");
            System.out.println("5) Aufgabe als erledigt markieren");
            System.out.println("6) Beenden");
            System.out.print("Auswahl: ");
            String choice = sc.nextLine();
            switch (choice) {
                case "1":
                    showTasks(manager.getTasks());
                    break;
                case "2":
                    addTask(manager, sc);
                    break;
                case "3":
                    deleteTask(manager, sc);
                    break;
                case "4":
                    filterTasks(manager, sc);
                    break;
                case "5":
                    markTaskDone(manager, sc);
                    break;
                case "6":
                    manager.saveTasksToFile();
                    System.out.println("Tschüss!");
                    return;
                default:
                    System.out.println("Ungültige Eingabe.");
            }
        }
    }

    static void showTasks(List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("Keine Aufgaben vorhanden.");
        } else {
            for (Task t : tasks) System.out.println(t);
        }
    }

    static void addTask(TaskManager manager, Scanner sc) {
        System.out.print("Titel: ");
        String title = sc.nextLine();
        System.out.print("Priorität (hoch/mittel/niedrig): ");
        String prio = sc.nextLine().toLowerCase();
        System.out.print("Fälligkeitsdatum (YYYY-MM-DD): ");
        String date = sc.nextLine();
        manager.addTask(title, prio, LocalDate.parse(date));
        System.out.println("Aufgabe hinzugefügt.");
    }

    static void deleteTask(TaskManager manager, Scanner sc) {
        System.out.print("ID der zu löschenden Aufgabe: ");
        int id = Integer.parseInt(sc.nextLine());
        manager.removeTask(id);
        System.out.println("Aufgabe gelöscht.");
    }

    static void filterTasks(TaskManager manager, Scanner sc) {
        System.out.print("Priorität zum Filtern (hoch/mittel/niedrig): ");
        String prio = sc.nextLine().toLowerCase();
        List<Task> filtered = manager.filterTasksByPriority(prio);
        showTasks(filtered);
    }

    static void markTaskDone(TaskManager manager, Scanner sc) {
        System.out.print("ID der Aufgabe zum Markieren: ");
        int id = Integer.parseInt(sc.nextLine());
        manager.markTaskDone(id);
        System.out.println("Aufgabe als erledigt markiert.");
    }
}
