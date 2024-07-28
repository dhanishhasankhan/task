import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ScheduleManager {
    private static ScheduleManager instance;
    private List<Task> tasks;

    private ScheduleManager() {
        tasks = new ArrayList<>();
    }

    public static synchronized ScheduleManager getInstance() {
        if (instance == null) {
            instance = new ScheduleManager();
        }
        return instance;
    }

    public boolean addTask(Task task) {
        for (Task t : tasks) {
            if (t.overlaps(task)) {
                System.out.println("Error: Task conflicts with existing task \"" + t.getDescription() + "\"");
                return false;
            }
        }
        tasks.add(task);
        System.out.println("Task added successfully. No conflicts.");
        return true;
    }

    public boolean removeTask(String description) {
        for (Task task : tasks) {
            if (task.getDescription().equals(description)) {
                tasks.remove(task);
                System.out.println("Task removed successfully.");
                return true;
            }
        }
        System.out.println("Error: Task not found.");
        return false;
    }

    public void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks scheduled for the day.");
            return;
        }
        Collections.sort(tasks, Comparator.comparing(Task::getStartTime));
        for (Task task : tasks) {
            System.out.println(task);
        }
    }

    public void viewTasksByPriority(String priority) {
        List<Task> filteredTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getPriority().equalsIgnoreCase(priority)) {
                filteredTasks.add(task);
            }
        }
        if (filteredTasks.isEmpty()) {
            System.out.println("No tasks with priority level: " + priority);
            return;
        }
        Collections.sort(filteredTasks, Comparator.comparing(Task::getStartTime));
        for (Task task : filteredTasks) {
            System.out.println(task);
        }
    }
}
