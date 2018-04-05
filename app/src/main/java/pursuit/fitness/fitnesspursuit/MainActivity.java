package pursuit.fitness.fitnesspursuit;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onClickSignIn();
    }

    private void
    showPopupMessage(String message) {
        Log.e(TAG, message);


        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, message, duration);
        toast.show();

        //SnackBar
        //CoordinatorLayout coordinatorLayout = findViewById(R.id.coordinator_layout);
        //Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_INDEFINITE).show();
    }



    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseUser user = auth.getCurrentUser();

    private static final int RC_SIGN_IN = 123;

    //private FirebaseAuth auth;
    public void onClickSignIn() {
        Log.d(TAG, "onClickSignIn");
        if (user != null) {
            showPopupMessage("already signed in, " + "displayName=" + user.getDisplayName()
                    + ", " + "email="
                    + user.getEmail() + ", " + "uuid=" + user.getUid());
        }
        else {
            List<AuthUI.IdpConfig> providers = Arrays.asList(
                    new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build());
// Create and launch sign -in intent
            startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder().setAvailableProviders(providers).build(),
                    RC_SIGN_IN
            );
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);
            if (resultCode == RESULT_OK) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                showPopupMessage("sign in successful, " + "displayName=" + user.getDisplayName() + ", " +
                        "email=" + user.getEmail() + ", " + "uuid=" + user.getUid());
            }
            else {
                if (response == null) {
                    showPopupMessage("sign in cancelled");
                    return;
                }
                if (response.getError().getErrorCode() == ErrorCodes.NO_NETWORK) {
                    showPopupMessage("no internet connection");
                    return;
                }
// handle all other errors
                showPopupMessage("Sign - in error: " + response.getError());
            }
        }
    }

}
