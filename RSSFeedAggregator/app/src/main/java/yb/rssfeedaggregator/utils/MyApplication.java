package yb.rssfeedaggregator.utils;

import android.app.Application;

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

}
