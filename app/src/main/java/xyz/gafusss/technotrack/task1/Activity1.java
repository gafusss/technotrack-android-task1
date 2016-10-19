package xyz.gafusss.technotrack.task1;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
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
        if (myApplication.waitTask == null) {
            myApplication.waitTask = new WaitTask(myApplication);
            myApplication.waitTask.execute();
        }
    }

    public void onTimer() {
        Intent intent = new Intent(this, Activity2.class);
        startActivity(intent);
        finish();
    }

    public class WaitTask extends AsyncTask<Void, Void, Void> {

        private MyApplication context;

        public WaitTask(MyApplication context) {
            this.context = context;
        }

        @Override
        protected Void doInBackground(Void... params) {
            Log.d(TAG, "doInBackground: gonna sleep");
            sleep(2000);
            Log.d(TAG, "doInBackground: woke up");
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            Log.d(TAG, "onPostExecute: !");
            context.activity1.onTimer();
            context.activity1 = null;
        }
    }
}
