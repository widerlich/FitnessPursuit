package pursuit.fitness.fitnesspursuit;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
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
                Intent intentProgress = new Intent(HomeActivity.this, QuestionnaireFabActivity.class);
                startActivity(intentProgress);
            }
        });

        final ImageButton imgbtn_food = (ImageButton) findViewById(R.id.imgbtn_food);
        imgbtn_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //imgbtn_food.setBackgroundColor(getResources().getColor(R.color.colorAccent));
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

        setAlarm();


    }

    public void minimizeApp() {
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Really Exit?")
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        moveTaskToBack(true);
                    }
                }).create().show();
    }


    private void setAlarm(){

        Intent intent = new Intent(this, NotificationReceiver.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 3000 , pendingIntent);
    }

}




//https://www.londonappdeveloper.com/how-to-use-git-hub-with-android-studio/