package css.nc.goaltracker;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

// Task Class.
@Entity(tableName = "tasks")
public class Task implements Parcelable {

    // Auto Generate function written by ChatGPT.
    @PrimaryKey(autoGenerate = true)
    private long id;

    // Title of the task.
    private String title;

    // Boolean if task is completed or not.
    private boolean completed;

    // Constructor to create a new Task object.
    public Task(String title, boolean completed) {
        this.title = title;
        this.completed = completed;
    }

    // Written by ChatGPT.
    protected Task(Parcel in) {
        id = in.readLong();
        title = in.readString();
        completed = in.readByte() != 0;
    }

    // Written by ChatGPT.
    public static final Creator<Task> CREATOR = new Creator<Task>() {
        @Override
        public Task createFromParcel(Parcel in) {
            return new Task(in);
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }
    };

    // Getter method to retrieve the task ID.
    public long getId() {
        return id;
    }

    // Setter method to set the task ID.
    public void setId(long id) {
        this.id = id;
    }

    // Getter method to retrieve the title.
    public String getTitle() {
        return title;
    }

    // Getter method to check if the task is completed.
    public boolean isCompleted() {
        return completed;
    }

    // Written by ChatGPT.
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(title);
        dest.writeByte((byte) (completed ? 1 : 0));
    }

    // Written by ChatGPT.
    @Override
    public int describeContents() {
        return 0;
    }
}
