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
    private TaskViewModel taskViewModel;
    public static final String EXTRA_TASK = "extra_task";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);

        EditText taskEditText = findViewById(R.id.taskEditText);
        Button saveButton = findViewById(R.id.saveButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
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

