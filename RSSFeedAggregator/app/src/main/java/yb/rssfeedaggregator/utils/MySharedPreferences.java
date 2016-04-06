package yb.rssfeedaggregator.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import yb.rssfeedaggregator.models.User;

/**
 * Shared preferences for app
 *
 * @version 1.0
 */
public class MySharedPreferences {

    public static final String
            SP_KEY = "yb.m5_mobile_application.sp",
            HAS_USER = "HAS_USER",
            USER = "USER";

    private SharedPreferences sharedPreferences;

    public MySharedPreferences(Context context) {
        sharedPreferences = context.getSharedPreferences(SP_KEY, Context.MODE_PRIVATE);
        setDefault();
    }

    private void setDefault() {
        sharedPreferences.edit().putBoolean(HAS_USER, false).apply();
    }

    public boolean hasUser() {
        return sharedPreferences.getBoolean(HAS_USER, false);
    }

    public User getUser() {
        if (hasUser())
            return new Gson().fromJson(sharedPreferences.getString(USER, ""), User.class);
        return null;
    }

    public void setUser(User user) {
        if (user != null) {
            sharedPreferences.edit().putString(new Gson().toJson(user, User.class), "").apply();
            sharedPreferences.edit().putBoolean(HAS_USER, true);
        }
    }

    public void clean() {
        sharedPreferences.edit().clear().apply();
    }

}