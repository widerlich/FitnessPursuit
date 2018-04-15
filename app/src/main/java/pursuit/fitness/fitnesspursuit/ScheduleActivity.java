package pursuit.fitness.fitnesspursuit;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class ScheduleActivity extends BaseActivity {

    public static String USER;

    @Override protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        setUpUI();
    }

    private void setUpUI() {
        Resources res = getResources();
        //        String[] days = res.getStringArray(R.array.day);
        String[] days = getResources().getStringArray(R.array.day);

        ListView lvDays = findViewById(R.id.listDays);
        //  ListView listView = day;
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

    private void goToActivity(Class clazz) {
        Intent intent = new Intent(ScheduleActivity.this, clazz);
        startActivity(intent);
    }

}
