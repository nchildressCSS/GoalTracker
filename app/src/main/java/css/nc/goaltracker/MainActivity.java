package css.nc.goaltracker;

// MainActivity.java
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TaskViewModel taskViewModel;
    private TaskAdapter taskAdapter;
    private List<Task> selectedTasks = new ArrayList<>();
    private static final int ADD_TASK_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);
        taskAdapter = new TaskAdapter(taskViewModel.getTasks(), selectedTasks);

        RecyclerView recyclerView = findViewById(R.id.taskRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(taskAdapter);

        Button addTaskButton = findViewById(R.id.addTaskButton);
        Button deleteTaskButton = findViewById(R.id.deleteTaskButton);

        addTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
                startActivityForResult(intent, ADD_TASK_REQUEST);
            }
        });

        deleteTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteSelectedTasks();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_TASK_REQUEST && resultCode == RESULT_OK) {
            Task newTask = data.getParcelableExtra(AddTaskActivity.EXTRA_TASK);
            taskViewModel.addTask(newTask);
            taskAdapter.notifyDataSetChanged();
        }
    }

    private void deleteSelectedTasks() {
        Iterator<Task> iterator = selectedTasks.iterator();
        while (iterator.hasNext()) {
            Task task = iterator.next();
            taskViewModel.deleteTask(task);
            iterator.remove();
        }

        taskAdapter.notifyDataSetChanged();
    }
}

