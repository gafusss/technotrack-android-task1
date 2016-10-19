package xyz.gafusss.technotrack.task1;

import android.app.Application;
import android.os.CountDownTimer;

public class MyApplication extends Application {

    public Activity1 activity1 = null;
    public long timerCounter = 2000;
    public CountDownTimer countDownTimer = null;

    public Activity2 activity2 = null;

}
