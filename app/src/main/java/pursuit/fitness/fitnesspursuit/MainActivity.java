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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        checkUser();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        checkUser();
    }

    @Override
    protected void onStart() {
        super.onStart();
        onClickSignIn();
    }

    private static final int RC_SIGN_IN = 123;

    //private FirebaseAuth auth;
    public void onClickSignIn() {
        if (user != null) {
            showPopupMessage("@string/sign_in_successful");//, " + "displayName=" + user.getDisplayName()
                    //+ ", " + "email="
                    //+ user.getEmail() + ", " + "uuid=" + user.getUid());
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
                showPopupMessage("@string/signin_successful");
                        //+ "displayName=" + user.getDisplayName() + ", " +
                        //"email=" + user.getEmail() + ", " + "uuid=" + user.getUid());
            }
            else {
                if (response == null) {
                    showPopupMessage("@string/signin_cancelled");
                    return;
                }
                if (response.getError().getErrorCode() == ErrorCodes.NO_NETWORK) {
                    showPopupMessage("@string/no_internet_connection");
                    return;
                }
                // handle all other errors
                showPopupMessage("@string/sign_in_error" + response.getError());
            }
        }
    }

}
