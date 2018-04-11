package pursuit.fitness.fitnesspursuit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    private TextView tv;
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        tv = (TextView) findViewById(R.id.welcome);
        iv = (ImageView) findViewById(R.id.logotext);
        Animation splash = AnimationUtils.loadAnimation(this, R.anim.transitionsplash);
        tv.startAnimation(splash);
        iv.startAnimation(splash);

        final Intent callMain = new Intent(this, MainActivity:class);
        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(2000);
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
