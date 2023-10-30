package css.nc.goaltracker;

import android.os.Parcel;
import android.os.Parcelable;

//Task Class.
public class Task implements Parcelable {
    //Task Variables.
    private String title;
    private boolean completed;

    //Task Constructor.
    public Task(String title) {
        this.title = title;
        this.completed = false;
    }

    public String getTitle() {
        return title;
    }

//Parcel Code written by ChatGPT.
    protected Task(Parcel in) {
        title = in.readString();
        completed = in.readByte() != 0;
    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(title);
        parcel.writeByte((byte) (completed ? 1 : 0));
    }
}
