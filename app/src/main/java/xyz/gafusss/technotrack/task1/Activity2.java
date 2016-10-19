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
    private MyApplication myApplication;

    private static final String TAG = "CountActivity";

    public Button button = null;
    public TextView textView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        Log.d(TAG, "onCreate: !");

        myApplication = (MyApplication) getApplicationContext();
        myApplication.activity2 = this;

        button = (Button)findViewById(R.id.button);
        textView = (TextView)findViewById(R.id.textView);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        Log.d(TAG, "onPostCreate: !");

        if (myApplication.countTask != null) {
            button.setText(R.string.button_stop);
        } else {
            button.setText(R.string.button_start);
            if (myApplication.counter == 1000) {
                textView.setText(R.string.s1000);
            } else {
                textView.setText(R.string.s0);
            }
        }
        
    }

    public void onButtonStart(View view) {
        Log.d(TAG, "onButtonStart: !");
        if (myApplication.countTask == null) {
            Log.d(TAG, "onButtonStart: start");
            button.setText(getString(R.string.button_stop));
            textView.setText(getString(R.string.s1));
            myApplication.countTask = new CountTask(myApplication);
            Log.d(TAG, "onButtonStart: created count task");
            myApplication.countTask.execute();
            Log.d(TAG, "onButtonStart: executed count task");
        } else {
            Log.d(TAG, "onButtonStart: stop:" + myApplication.countTask.cancel(true));
            myApplication.countTask = null;
            myApplication.counter = 1;
            button.setText(getString(R.string.button_start));
            textView.setText(getString(R.string.s0));
        }
    }

    public class CountTask extends AsyncTask<Void, Void, Void> {

        private MyApplication myApplication;

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

        CountTask(MyApplication myApplication)
        {
            Log.d(TAG, "CountTask: CONSTRUCTORRR");
            this.myApplication = myApplication;
        }

        @Override
        protected Void doInBackground(Void... params) {
            Log.d(TAG, "doInBackground: !");
            while (myApplication.counter < 1000) {
                if (isCancelled()) {
                    return null;
                }
                publishProgress();
                sleep(1000);
                myApplication.counter++;
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
            int counter = myApplication.counter;
            int c = counter % 10;
            int b = (counter / 10) % 10;
            int a = (counter / 100) % 10;

            String sc;
            String sb;
            String sa;

            if (b == 1) {
                sc = "";
                sb = s10[c];
            } else {
                sc = s[0][c];
                sb = s[1][b];
            }
            sa = s[2][a];

            String text = sa.isEmpty() ? sb.isEmpty() ? sc : sc.isEmpty() ? sb : sb + ' ' + sc : sb.isEmpty() ? sc.isEmpty() ? sa : sa + ' ' + sc : sa + ' '+ sb + ' ' + sc;
            myApplication.activity2.textView.setText(text);
        }

        @Override
        protected void onPostExecute(Void result) {
            Log.d(TAG, "onPostExecute: !");
            myApplication.activity2.textView.setText(R.string.s1000);
            myApplication.activity2.button.setText(R.string.button_start);
            myApplication.counter = 1;
            myApplication.countTask = null;
        }
    }
}
