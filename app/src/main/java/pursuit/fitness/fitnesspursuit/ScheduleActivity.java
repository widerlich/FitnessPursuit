package pursuit.fitness.fitnesspursuit;

import android.app.ListActivity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ScheduleActivity extends ListActivity {

    DatabaseHelper mDatabaseHelper;
    private String TAG = "ScheduleActivity";

    @Override protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        populateListView();
        setUpUI();
    }

    private void setUpUI() {


        ListView lvDays = findViewById(R.id.listDays);

        AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
                // handle click

                Intent intent = new Intent(ScheduleActivity.this, DayActivity.class);
                intent.putExtra(DayActivity.EXTRA_DAY_ID, (int) id);
                startActivity(intent);

            }
        };
        lvDays.setOnItemClickListener(onItemClickListener);
    }


    private void populateListView() {


        Log.d(TAG, "populateListView: Displaying data in the ListView.");

        //get the data and append to a list
        Cursor data = mDatabaseHelper.getDay();
        ArrayList<String> listData = new ArrayList<>();
        while (data.moveToNext()) {
            //get the value from the database in column 1
            //then add it to the ArrayList
            listData.add(data.getString(1));

        }
    }

}
