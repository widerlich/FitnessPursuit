package pursuit.fitness.fitnesspursuit;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class HomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_home);


        ImageButton imgbtn_schedule = (ImageButton) findViewById(R.id.imgbtn_schedule);
        imgbtn_schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSchedule = new Intent(HomeActivity.this, ScheduleActivity.class);
                startActivity(intentSchedule);
            }
        });

        ImageButton imgbtn_progress = (ImageButton) findViewById(R.id.imgbtn_progress);
        imgbtn_progress.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intentProgress = new Intent(HomeActivity.this, ProgressActivity.class);
                startActivity(intentProgress);
            }
        });

        ImageButton imgbtn_friends = (ImageButton) findViewById(R.id.imgbtn_food);
        imgbtn_friends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentname = new Intent(HomeActivity.this, CalendarActivity.class);
                startActivity(intentname);
            }
        });


        ImageButton imgbtn_profile = (ImageButton) findViewById(R.id.imgbtn_profile);
        imgbtn_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentProfile = new Intent(HomeActivity.this, ProfileActivity.class);
                startActivity(intentProfile);
            }
        });

    }
}




//https://www.londonappdeveloper.com/how-to-use-git-hub-with-android-studio/