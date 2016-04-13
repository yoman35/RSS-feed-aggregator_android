package yb.rssfeedaggregator.utils;

import android.app.Application;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

import yb.rssfeedaggregator.R;

/**
 * Application singleton to help context resolution
 *
 * @version 1.0
 */
public class MyApplication extends Application {
    private static MyApplication singleton;
    private MySharedPreferences mSP;

    public static MyApplication getInstance() {
        return singleton;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        singleton = this;
        mSP = new MySharedPreferences(this);
    }

    public MySharedPreferences getSP() {
        return mSP;
    }

    private Tracker mTracker;

    /**
     * Gets the default {@link Tracker} for this {@link Application}.
     * @return tracker
     */
    synchronized public Tracker getDefaultTracker() {
        if (mTracker == null) {
            GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
            // To enable debug logging use: adb shell setprop log.tag.GAv4 DEBUG
            mTracker = analytics.newTracker(R.xml.app_tracker);
        }
        return mTracker;
    }


}
