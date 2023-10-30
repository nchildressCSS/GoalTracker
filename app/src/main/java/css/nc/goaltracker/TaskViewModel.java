package css.nc.goaltracker;

import androidx.lifecycle.ViewModel;
import java.util.ArrayList;
import java.util.List;

//Task View Model Class.
public class TaskViewModel extends ViewModel {
    //Initialize Variables.
    private List<Task> tasks = new ArrayList<>();

    //Constructor
    public List<Task> getTasks() {
        return tasks;
    }

    //Add Task.
    public void addTask(Task task) {
        tasks.add(task);
    }

    //Delete Task.
    public void deleteTask(Task task) {
        tasks.remove(task);
    }
}
