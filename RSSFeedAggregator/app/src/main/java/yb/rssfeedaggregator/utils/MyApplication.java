package yb.rssfeedaggregator.utils;

import android.app.Application;
import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

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

    public void loadBitmapViaPicasso(Context context, String url, ImageView holder) {
        Picasso.with(context).load(url).into(holder);
    }

}
