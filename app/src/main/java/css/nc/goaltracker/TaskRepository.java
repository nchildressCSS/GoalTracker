package css.nc.goaltracker;

import android.app.Application;
import android.util.Log;
import androidx.lifecycle.LiveData;
import java.util.List;

// TaskRepository Class.
public class TaskRepository {

    // TaskDao to interact with the database.
    private TaskDao taskDao;

    // LiveData list to check for changes in the tasks list.
    private LiveData<List<Task>> allTasks;

    // Constructor to initialize the TaskRepository.
    public TaskRepository(Application application) {
        TaskDatabase database = TaskDatabase.getInstance(application);
        taskDao = database.taskDao();

        // Get LiveData of all tasks from the TaskDao.
        allTasks = taskDao.getAllTasks();
    }

    // Getter method to retrieve LiveData of all tasks.
    public LiveData<List<Task>> getAllTasks() {
        return allTasks;
    }

    // Insert a new task into the database using a background thread.
    public void insertTask(Task task) {
        TaskDatabase.databaseWriteExecutor.execute(() -> {
            taskDao.insertTask(task);
        });

        // Log a message when insertTask is called.
        Log.d("TaskRepository", "insertTask called");
    }

    // Delete a task from the database using a background thread.
    public void deleteTask(Task task) {
        TaskDatabase.databaseWriteExecutor.execute(() -> {
            taskDao.deleteTask(task);
        });
    }
}
