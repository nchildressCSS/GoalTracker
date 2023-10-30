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

//Task Adapter Class.
public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {
    //Variables Declaration.
    private List<Task> tasks;
    private List<Task> selectedTasks;

    //Constructor.
    public TaskAdapter(List<Task> tasks, List<Task> selectedTasks) {
        this.tasks = tasks;
        this.selectedTasks = selectedTasks;
    }

    //Create View holder and inflate with item xml.
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent, false);
        return new ViewHolder(view);
    }

    //Bind View Holder filled with task information.
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Task task = tasks.get(position);
        holder.titleTextView.setText(task.getTitle());
        holder.completedCheckBox.setChecked(selectedTasks.contains(task));

        holder.completedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            //check if task is checked or not and remove if checked.
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    selectedTasks.add(task);
                } else {
                    selectedTasks.remove(task);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    //Creates Recyclerview.
    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        CheckBox completedCheckBox;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            completedCheckBox = itemView.findViewById(R.id.completedCheckBox);
        }
    }
}
