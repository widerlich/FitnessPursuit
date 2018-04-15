package pursuit.fitness.fitnesspursuit;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

abstract class BaseActivity extends AppCompatActivity {
    FirebaseUser user;
    FirebaseAuth auth;

    protected void checkUser() {
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        if (user!=null) {
            String userid = user.getUid();
            Intent proceedToSchedule = new Intent(this, ScheduleActivity.class);
            proceedToSchedule.putExtra(ScheduleActivity.USER, userid);
            startActivity(proceedToSchedule);
        }
        else{
            showPopupMessage("@string/toast_not_logged_in");
        }
    }
    //Toast
    protected void
    showPopupMessage(String message) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, message, duration);
        toast.show();
    }

    //Snackbar
     /*   protected void
        showPopupMessage(String message, View v, String actionname, Activity activity) {
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_LONG;
            Snackbar.make(v, message, Snackbar.LENGTH_LONG).
                    .setAction(actionname, new View.onClickListener() {
            @Override public void onClick(View v){
                Intent move = new Intent(this, activity);
                startActivity(move);
            };//.show();
            }

            }*/

}
