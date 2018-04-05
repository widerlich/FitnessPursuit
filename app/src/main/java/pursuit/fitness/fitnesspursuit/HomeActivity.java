package pursuit.fitness.fitnesspursuit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity {

    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseUser user = auth.getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        ImageButton imgbtn_schedule = (ImageButton) findViewById(R.id.imgbtn_schedule);
        imgbtn_schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentname = new Intent(HomeActivity.this, ScheduleActivity.class);
                startActivity(intentname);
            }
        });

        ImageButton imgbtn_progress = (ImageButton) findViewById(R.id.imgbtn_progress);
        imgbtn_progress.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intentname = new Intent(HomeActivity.this, ProgressActivity.class);
                startActivity(intentname);
            }
        });

        ImageButton imgbtn_friends = (ImageButton) findViewById(R.id.imgbtn_friends);
        imgbtn_friends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentname = new Intent(HomeActivity.this, FriendsActivity.class);
                startActivity(intentname);
            }
        });


        ImageButton imgbtn_profile = (ImageButton) findViewById(R.id.imgbtn_profile);
        imgbtn_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentname = new Intent(HomeActivity.this, ProfileActivity.class);
                startActivity(intentname);
            }
        });

    }
}




//https://www.londonappdeveloper.com/how-to-use-git-hub-with-android-studio/