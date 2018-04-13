package pursuit.fitness.fitnesspursuit;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

abstract class BaseActivity extends AppCompatActivity {
    FirebaseUser user;
    FirebaseAuth auth;

    protected void checkUser() {
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        if (user==null)
            showPopupMessage("Please log in or create a profile.");
        else{
            String userid = user.getUid();
            Intent proceedToSchedule = new Intent(this, ScheduleActivity.class);
            proceedToSchedule.putExtra(ScheduleActivity.USER, userid);
            startActivity(proceedToSchedule);
        }
    }

    protected void
    showPopupMessage(String message) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, message, duration);
        toast.show();

        //SnackBar
        //CoordinatorLayout coordinatorLayout = findViewById(R.id.coordinator_layout);
        //Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_INDEFINITE).show();
    }
}
