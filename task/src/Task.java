import java.util.ArrayList;
import java.util.List;
class Task {
String description;
String startTime;
String endTime;
String priority;
boolean completed;
Task(String description, String startTime, String endTime, String priority) {
this.description = description;
this.startTime = startTime;
this.endTime = endTime;
this.priority = priority;
this.completed = false;
}
}
class TaskFactory {
public static Task createTask(String description, String startTime, String endTime, String
priority) {
return new Task(description, startTime, endTime, priority);
}
}
interface Observer {
void update(String message);
}
class ScheduleManager {
private static ScheduleManager instance;
private List<Task> tasks;
private List<Observer> observers;
private ScheduleManager() {
tasks = new ArrayList<>();
observers = new ArrayList<>();
}
public static ScheduleManager getInstance() {
if (instance == null) {
instance = new ScheduleManager();
} return instance;
}
public boolean addTask(Task task) {
if (isConflict(task)) {
notifyObservers("Task '" + task.description + "' conflicts with existing tasks.");
return false;
} tasks.add(task);
System.out.println("Task '" + task.description + "' added successfully.");
return true;
}
public void removeTask(String description) {
for (Task task : tasks) {
if (task.description.equals(description)) {
tasks.remove(task);
System.out.println("Task '" + description + "' removed successfully.");
return;
}
}
System.out.println("Error: Task not found.");
}
public void viewTasks() {
if (tasks.isEmpty()) {
System.out.println("No tasks scheduled for the day.");
return;
} tasks.sort((t1, t2) -> t1.startTime.compareTo(t2.startTime));
for (Task task : tasks) {
String status = task.completed ? "Completed" : "Pending";
System.out.println(task.startTime + " - " + task.endTime + ": " + task.description +
" [" + task.priority + "] (" + status + ")");
}
}
private boolean isConflict(Task newTask) {
for (Task task : tasks) {
if (task.startTime.compareTo(newTask.endTime) < 0 &&
newTask.startTime.compareTo(task.endTime) < 0) {
return true;
}
} return false;
}
public void addObserver(Observer observer) {
observers.add(observer);
}
private void notifyObservers(String message) {
for (Observer observer : observers) {
observer.update(message);
}
}
}
public class main {
public static void main(String[] args) {
ScheduleManager manager = ScheduleManager.getInstance();
Observer observer = message -> System.out.println("Notification: " + message);
manager.addObserver(observer);
Task task1 = TaskFactory.createTask("Morning Exercise", "07:00", "08:00", "High");
manager.addTask(task1);
Task task2 = TaskFactory.createTask("Team Meeting", "09:00", "10:00", "Medium");
manager.addTask(task2);
manager.viewTasks();
Task task3 = TaskFactory.createTask("Training Session", "09:30", "10:30", "High");
manager.addTask(task3);
}
}
