import java.util.Scanner;

public class SchedulerApp {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ScheduleManager manager = ScheduleManager.getInstance();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n1. Add Task");
            System.out.println("2. Remove Task");
            System.out.println("3. View All Tasks");
            System.out.println("4. View Tasks by Priority");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addTask();
                    break;
                case 2:
                    removeTask();
                    break;
                case 3:
                    manager.viewTasks();
                    break;
                case 4:
                    viewTasksByPriority();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void addTask() {
        System.out.print("Enter task description: ");
        String description = scanner.nextLine();
        System.out.print("Enter start time (HH:mm): ");
        String startTime = scanner.nextLine();
        System.out.print("Enter end time (HH:mm): ");
        String endTime = scanner.nextLine();
        System.out.print("Enter priority (Low/Medium/High): ");
        String priority = scanner.nextLine();

        try {
            Task task = TaskFactory.createTask(description, startTime, endTime, priority);
            manager.addTask(task);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void removeTask() {
        System.out.print("Enter task description to remove: ");
        String description = scanner.nextLine();
        manager.removeTask(description);
    }

    private static void viewTasksByPriority() {
        System.out.print("Enter priority level to view (Low/Medium/High): ");
        String priority = scanner.nextLine();
        manager.viewTasksByPriority(priority);
    }
}
