package css.nc.goaltracker;

// MyApplication Class.
import android.app.Application;

// Written by ChatGPT.
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        TaskDatabase.getInstance(this);
    }
}

