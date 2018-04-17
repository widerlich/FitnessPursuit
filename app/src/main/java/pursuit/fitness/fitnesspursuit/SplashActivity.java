package pursuit.fitness.fitnesspursuit;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends BaseActivity {

    private TextView tv1;
    private TextView tv2;
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        tv1 = (TextView) findViewById(R.id.welcome);
        tv2 = (TextView) findViewById(R.id.intro);
        iv = (ImageView) findViewById(R.id.logotext);
        Animation splash = AnimationUtils.loadAnimation(this, R.anim.transitionsplash);
        tv1.startAnimation(splash);
        tv2.startAnimation(splash);
        iv.startAnimation(splash);

        final Intent callMain = new Intent(this, QuestionnaireFabActivity.class);
        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(2500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    startActivity(callMain);
                    finish();
                }
            }
        };
        timer.start();
    }
}

