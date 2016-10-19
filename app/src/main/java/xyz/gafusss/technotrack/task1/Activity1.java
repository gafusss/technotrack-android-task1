package xyz.gafusss.technotrack.task1;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import static android.os.SystemClock.sleep;

public class Activity1 extends AppCompatActivity {

    private MyApplication myApplication;

    private static final String TAG = "ImageActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);

        myApplication = (MyApplication) getApplicationContext();
        myApplication.activity1 = this;
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Log.d(TAG, "onPostResume: !");

        if (myApplication.countDownTimer != null) {
            myApplication.countDownTimer.cancel();
        }
        myApplication.countDownTimer = new CountDownTimer(myApplication.timerCounter, 1) {
            @Override
            public void onTick(long millisUntilFinished) {
                myApplication.timerCounter = millisUntilFinished;
            }

            @Override
            public void onFinish() {
                onTimer();
            }
        }.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: !");
        myApplication.countDownTimer.cancel();
        myApplication.countDownTimer = null;
    }

    public void onTimer() {
        Intent intent = new Intent(this, Activity2.class);
        startActivity(intent);
        finish();
    }
}
