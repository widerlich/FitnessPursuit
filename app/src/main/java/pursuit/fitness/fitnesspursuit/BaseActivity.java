package pursuit.fitness.fitnesspursuit;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

abstract class BaseActivity extends AppCompatActivity {
    FirebaseUser user;
    FirebaseAuth auth;
    String userid;

    protected void checkUser() {
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        if (user==null) {
            showPopupMessage(getString(R.string.toast_not_logged_in));
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    public void signOutUser() {
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    public void onComplete(@NonNull Task<Void> task) {
                        showPopupMessage(getString(R.string.sign_out_successful));
                    }
                });
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    public void deleteUser() {
        AuthUI.getInstance()
                .delete(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    public void onComplete(@NonNull Task<Void> task) {
                        showPopupMessage(getString(R.string.user_deleted));
                    }
                });
        //TODO
        //SQLite Delete Row Statement
        //DELETE
        //FROM
        // table
        //WHERE
        //search_condition;
        signOutUser();
    }

    //Toast
    protected void
    showPopupMessage(String message) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
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
