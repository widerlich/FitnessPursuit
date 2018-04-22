package pursuit.fitness.fitnesspursuit;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends BaseActivity {

    Database db;
    FirebaseUser user;
    String userid = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        onClickSignIn();
        if(user!=null)
            userid = user.getUid();
    }

    protected void onResume(Bundle savedInstanceState){
        intent1 = new Intent(MainActivity.this, HomeActivity.class);
        startActivity(intent1);
    }

    private static final int RC_SIGN_IN = 123;
    Intent intent1;
    Intent intent2;
    String [] array = new String [1];


    //private FirebaseAuth auth;
    public void onClickSignIn() {
        if (user != null) {
            showPopupMessage(getString(R.string.successful_sign_in));
            intent1 = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent1);

         /*   try {
                Cursor c = db.rawQuery("SELECT username FROM users WHERE id_user="+userid, array);

                if(c.getCount()>0)
                    intent1 = new Intent(MainActivity.this, HomeActivity.class);
                else
                    intent2 = new Intent(MainActivity.this, QuestionnaireActivity2.class);
            }catch(Exception e){}
         */
        }
        else {
            List<AuthUI.IdpConfig> providers = Arrays.asList(
                    new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build(),
                    new AuthUI.IdpConfig.GoogleBuilder().build());
            // Create and launch sign -in intent
            startActivityForResult(AuthUI       .getInstance().createSignInIntentBuilder()
                                                .setAvailableProviders(providers).build(), RC_SIGN_IN
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
                showPopupMessage(getString(R.string.successful_sign_in));
                intent1 = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent1);
            }

            else {
                if (response == null) {
                    showPopupMessage(getString(R.string.sign_in_cancelled));
                    return;
                }
                if (response.getError().getErrorCode() == ErrorCodes.NO_NETWORK) {
                    showPopupMessage(getString(R.string.no_internet_connection));
                    return;
                }
                // handle all other errors
                showPopupMessage(getString(R.string.signin_error) + response.getError());
            }
        }
    }

}
