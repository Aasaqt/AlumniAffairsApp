package uiet.alumni;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import uiet.alumni.Activity.HomeActivity;
import uiet.alumni.Activity.MainActivity;
import uiet.alumni.utils.PrefUtils;

/**
 * Created by aasaqt on 29/10/15.
 */
public class SplashScreen extends Activity{
    // Splash screen timer
    private static int SPLASH_TIME_OUT = 2500;
    Animation anim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ImageView ivlogo=(ImageView) findViewById(R.id.imgLogo);
        anim = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.slide_down);
        ivlogo.startAnimation(anim);

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                    if(!PrefUtils.getUserLogin()) {
                        Intent p = new Intent(SplashScreen.this, MainActivity.class);
                        startActivity(p);
                    }else{
                        Intent i = new Intent(SplashScreen.this, HomeActivity.class);
                        startActivity(i);
                    }
                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
