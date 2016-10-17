package xyz.gafusss.technotrack.task1;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import static android.os.SystemClock.sleep;

public class Activity1 extends AppCompatActivity {

    private static final String TAG = "ImageActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Log.d(TAG, "onPostResume: !");
        WaitTask task = new WaitTask(this);
        task.execute();
    }


    private class WaitTask extends AsyncTask<Void, Void, Void> {

        private Context context;

        public WaitTask(Context context) {
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
            Intent intent = new Intent(context, Activity2.class);
            startActivity(intent);
            finish();
        }
    }
}
