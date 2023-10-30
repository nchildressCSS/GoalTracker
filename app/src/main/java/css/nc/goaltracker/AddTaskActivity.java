package css.nc.goaltracker;

// AddTaskActivity.java
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class AddTaskActivity extends AppCompatActivity {
    //Variable Declaration.
    private TaskViewModel taskViewModel;
    public static final String EXTRA_TASK = "extra_task";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        //Set View Model up.
        taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);

        //Initialize edit text field and save button from xml.
        EditText taskEditText = findViewById(R.id.taskEditText);
        Button saveButton = findViewById(R.id.saveButton);

        //Create listener for save button.
        saveButton.setOnClickListener(new View.OnClickListener() {
            //Create logic when the save button is clicked.
            @Override
            public void onClick(View view) {
                String taskTitle = taskEditText.getText().toString();
                if (!taskTitle.isEmpty()) {
                    Task newTask = new Task(taskTitle);

                    Intent resultIntent = new Intent();
                    resultIntent.putExtra(EXTRA_TASK, newTask);
                    setResult(RESULT_OK, resultIntent);
                    finish();
                }
            }
        });
    }
}

