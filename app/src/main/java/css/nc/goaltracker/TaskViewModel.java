package css.nc.goaltracker;

import android.app.Application;
import android.util.Log;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;

// Task ViewModel Class.
public class TaskViewModel extends AndroidViewModel {

    // Repository to interact with the data source.
    private TaskRepository repository;

    // LiveData to check for changes in the tasks list.
    private LiveData<List<Task>> allTasks;

    // Constructor to initialize the TaskViewModel.
    public TaskViewModel(Application application) {
        super(application);

        // Create an instance of the TaskRepository.
        repository = new TaskRepository(application);
        allTasks = repository.getAllTasks();
    }

    // Getter method to retrieve LiveData of all tasks.
    public LiveData<List<Task>> getAllTasks() {
        return allTasks;
    }

    // Insert task in repository.
    public void insertTask(Task task) {
        repository.insertTask(task);

        // Log a message when insertTask is called.
        Log.d("TaskViewModel", "insertTask called");
    }

    // Delete Task in repository.
    public void deleteTask(Task task) {
        repository.deleteTask(task);
    }
}
