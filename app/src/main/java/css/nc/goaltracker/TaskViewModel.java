package css.nc.goaltracker;

// TaskViewModel.java
import androidx.lifecycle.ViewModel;
import java.util.ArrayList;
import java.util.List;

public class TaskViewModel extends ViewModel {
    private List<Task> tasks = new ArrayList<>();

    public List<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(Task task) {
        tasks.remove(task);
    }
}
