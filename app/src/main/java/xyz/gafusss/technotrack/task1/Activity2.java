package xyz.gafusss.technotrack.task1;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static android.os.SystemClock.sleep;

public class Activity2 extends AppCompatActivity {

    private static final String TAG = "CountActivity";

    private boolean started = false;
    private int counter = 1;

    CountTask countTask = null;

    Button button = null;
    TextView textView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        Log.d(TAG, "onCreate: !");
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        Log.d(TAG, "onPostCreate: !");

        button = (Button)findViewById(R.id.button);
        textView = (TextView)findViewById(R.id.textView);

        if (started && countTask == null) {
            button.setText(R.string.button_stop);
            countTask = new CountTask(this);
            countTask.execute();
        } else {
            button.setText(R.string.button_start);
        }
        
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Log.d(TAG, "onPostResume: !");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: !");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: !");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        countTask.cancel(true);
        Log.d(TAG, "onDestroy: !");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("counter", counter);
        outState.putBoolean("started", started);
        Log.d(TAG, "onSaveInstanceState: !");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG, "onRestoreInstanceState: !");
        counter = savedInstanceState.getInt("counter");
        started = savedInstanceState.getBoolean("started");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: !");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: !");
    }

    public void onButtonStart(View view) {
        Log.d(TAG, "onButtonStart: !");
        started = !started;
        if (started) {
            Log.d(TAG, "onButtonStart: start");
            button.setText(getString(R.string.button_stop));
            textView.setText(getString(R.string.s1));
            countTask = new CountTask(this);
            Log.d(TAG, "onButtonStart: created count task");
            countTask.execute();
            Log.d(TAG, "onButtonStart: executed count task");
        } else {
            Log.d(TAG, "onButtonStart: stop:" + countTask.cancel(true));
            counter = 1;
            button.setText(getString(R.string.button_start));
            textView.setText(getString(R.string.s0));
        }
    }


    private class CountTask extends AsyncTask<Void, Void, Void> {

        private Context context;

        private String s[][] = {
                {
                        getString(R.string.s0),
                        getString(R.string.s1),
                        getString(R.string.s2),
                        getString(R.string.s3),
                        getString(R.string.s4),
                        getString(R.string.s5),
                        getString(R.string.s6),
                        getString(R.string.s7),
                        getString(R.string.s8),
                        getString(R.string.s9)
                }, {
                getString(R.string.s00),
                getString(R.string.s10),
                getString(R.string.s20),
                getString(R.string.s30),
                getString(R.string.s40),
                getString(R.string.s50),
                getString(R.string.s60),
                getString(R.string.s70),
                getString(R.string.s80),
                getString(R.string.s90)
        }, {
                getString(R.string.s000),
                getString(R.string.s100),
                getString(R.string.s200),
                getString(R.string.s300),
                getString(R.string.s400),
                getString(R.string.s500),
                getString(R.string.s600),
                getString(R.string.s700),
                getString(R.string.s800),
                getString(R.string.s900)
        }
        };

        private String s10[] = {
                getString(R.string.s10),
                getString(R.string.s11),
                getString(R.string.s12),
                getString(R.string.s13),
                getString(R.string.s14),
                getString(R.string.s15),
                getString(R.string.s16),
                getString(R.string.s17),
                getString(R.string.s18),
                getString(R.string.s19),
        };

        //private int counter = 1;

        public CountTask(Context context)
        {
            Log.d(TAG, "CountTask: CONSTRUCTORRR");
            this.context = context;
        }

        @Override
        protected Void doInBackground(Void... params) {
            Log.d(TAG, "doInBackground: !");
            while (counter < 1000) {
                if (isCancelled()) {
                    return null;
                }
                publishProgress();
                sleep(1000);
                counter++;
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d(TAG, "onPreExecute: !");
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            Log.d(TAG, "onProgressUpdate: !");
            int c = counter % 10;
            int b = (counter / 10) % 10;
            int a = (counter / 100) % 10;

            String sc = null;
            String sb = null;
            String sa = null;

            if (b == 1) {
                sc = "";
                sb = s10[c];
            } else {
                sc = s[0][c];
                sb = s[1][b];
            }
            sa = s[2][a];

            String text = sa.isEmpty() ? sb.isEmpty() ? sc : sc.isEmpty() ? sb : sb + ' ' + sc : sb.isEmpty() ? sc.isEmpty() ? sa : sa + ' ' + sc : sa + ' '+ sb + ' ' + sc;
            textView.setText(text);
        }

        @Override
        protected void onPostExecute(Void result) {
            Log.d(TAG, "onPostExecute: !");
            textView.setText(R.string.s1000);
            button.setText(R.string.button_start);
            started = false;
            counter = 1;
        }
    }
}
