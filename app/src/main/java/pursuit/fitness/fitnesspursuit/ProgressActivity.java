package pursuit.fitness.fitnesspursuit;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

public class ProgressActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
    }
}
