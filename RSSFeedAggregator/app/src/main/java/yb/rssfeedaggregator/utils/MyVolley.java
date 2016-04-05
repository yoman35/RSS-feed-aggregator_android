package yb.rssfeedaggregator.utils;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Volley library singleton
 *
 * @version 1.0
 */
public class MyVolley {
    public static MyVolley instance_ = null;
    private RequestQueue requestQueue_;

    private MyVolley(Context context) {
        requestQueue_ = Volley.newRequestQueue(context);
    }

    public static MyVolley getInstance(Context context) {
        if (instance_ == null) instance_ = new MyVolley(context);
        return instance_;
    }

    public RequestQueue getRequestQueue() {
        return requestQueue_;
    }
}
