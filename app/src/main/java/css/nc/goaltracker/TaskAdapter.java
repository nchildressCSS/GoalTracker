package css.nc.goaltracker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

// TaskAdapter Class.
public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    // List of tasks for recycler.
    private List<Task> tasks;

    // List of selected tasks.
    private List<Task> selectedTasks;

    // Constructor to initialize the TaskAdapter with tasks and selectedTasks.
    public TaskAdapter(List<Task> tasks, List<Task> selectedTasks) {
        this.tasks = tasks;
        this.selectedTasks = selectedTasks;
    }

    // Update the list of tasks and notify the adapter of the data change.
    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
        notifyDataSetChanged();
    }

    // Create and return a new ViewHolder for the RecyclerView.
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for a task item.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent, false);
        return new ViewHolder(view);
    }

    // Bind the data of a task to a ViewHolder.
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Task task = tasks.get(position);
        holder.titleTextView.setText(task.getTitle());
        holder.completedCheckBox.setChecked(selectedTasks.contains(task));

        // Set a listener to handle changes in the CheckBox state.
        holder.completedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Update the list of selected tasks based on CheckBox changes.
                if (isChecked) {
                    selectedTasks.add(task);
                } else {
                    selectedTasks.remove(task);
                }
            }
        });
    }

    // Return the total number of tasks in the RecyclerView.
    @Override
    public int getItemCount() {
        return tasks.size();
    }

    // ViewHolder class to represent the views of a task item.
    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        CheckBox completedCheckBox;

        // Constructor to initialize the views in a ViewHolder.
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            completedCheckBox = itemView.findViewById(R.id.completedCheckBox);
        }
    }
}
