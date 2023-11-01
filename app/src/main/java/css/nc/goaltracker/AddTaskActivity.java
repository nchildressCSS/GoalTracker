package css.nc.goaltracker;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

// AddTaskActivity Class.
public class AddTaskActivity extends AppCompatActivity {

    // Create ViewModel.
    private TaskViewModel taskViewModel;

    // Create Key.
    public static final String EXTRA_TASK = "extra_task";

    //Check if result is false.
    private boolean isTaskResultSet = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("AddTaskActivity", "onCreate called");

        // Set the current layout for activity add task.
        setContentView(R.layout.activity_add_task);

        // Initialize the ViewModel for this activity.
        taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);

        //Get UI from XML.
        EditText taskEditText = findViewById(R.id.taskEditText);
        Button saveButton = findViewById(R.id.saveButton);

        // Set a click listener for the Save Button.
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the task title from the EditText.
                String taskTitle = taskEditText.getText().toString();

                // Check if the task title is not empty.
                if (!taskTitle.isEmpty()) {
                    // Create a new Task object.
                    Task newTask = new Task(taskTitle, false);

                    // Insert the new task using the ViewModel.
                    taskViewModel.insertTask(newTask);

                    // Prepare an Intent to pass the new task to the calling activity.
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra(EXTRA_TASK, newTask);

                    // Set the result code and include the Intent data.
                    setResult(RESULT_OK, resultIntent);

                    // Finish the activity.
                    finish();
                    Log.d("AddTaskActivity", "Finish Called");
                }
            }
        });
    }

    // Written by ChatGPT.
    @Override
    public void finish() {
        Intent resultIntent = new Intent();
        resultIntent.putExtra(EXTRA_TASK, false);

        if (!isTaskResultSet) {

            setResult(RESULT_OK, resultIntent);


            isTaskResultSet = true;
        }

        super.finish();
    }
}
