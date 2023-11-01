package css.nc.goaltracker;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

// MainActivity class.
public class MainActivity extends AppCompatActivity {

    // Create ViewModel.
    private TaskViewModel taskViewModel;

    // Create Adapter for Recycler.
    private TaskAdapter taskAdapter;

    // Create list for the selected items.
    private List<Task> selectedTasks = new ArrayList<>();

    // Request to add task.
    private static final int ADD_TASK_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("MainActivity", "onCreate called");

        // Set the current layout for activity main.
        setContentView(R.layout.activity_main);

        // Initialize the ViewModel for this activity.
        taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);

        // Create an adapter for the RecyclerView.
        taskAdapter = new TaskAdapter(new ArrayList<>(), selectedTasks);

        // Set up the RecyclerView and the adapter.
        RecyclerView recyclerView = findViewById(R.id.taskRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(taskAdapter);

        // Observer to check if changes were made.
        taskViewModel.getAllTasks().observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> tasks) {
                // Update the UI when the task list changes.
                taskAdapter.setTasks(tasks);
            }
        });

        //Get UI from XML.
        Button addTaskButton = findViewById(R.id.addTaskButton);
        Button deleteTaskButton = findViewById(R.id.deleteTaskButton);

        // create a click listener for the Add Task button.
        addTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start the AddTaskActivity to add a new task.
                Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
                startActivityForResult(intent, ADD_TASK_REQUEST);
            }
        });

        // Create a click listener for the Delete Task button.
        deleteTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Delete the selected tasks.
                deleteSelectedTasks();
            }
        });
    }

    // Handle the result from the AddTaskActivity.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("MainActivity", "onActivityResult called");

        // Check if the result is from adding a new task.
        if (requestCode == ADD_TASK_REQUEST && resultCode == RESULT_OK) {
            // Get the new task from the Intent data.
            Task newTask = data.getParcelableExtra(AddTaskActivity.EXTRA_TASK);

            // Insert the new task into the ViewModel.
            if (newTask != null) {
                taskViewModel.insertTask(newTask);
            }
        }
    }

    // Delete the selected tasks.
    private void deleteSelectedTasks() {
        for (Task task : selectedTasks) {
            taskViewModel.deleteTask(task);
        }

        // Clear the list of selected tasks.
        selectedTasks.clear();
    }
}
