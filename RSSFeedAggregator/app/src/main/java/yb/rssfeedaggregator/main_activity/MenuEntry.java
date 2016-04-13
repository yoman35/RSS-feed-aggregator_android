package yb.rssfeedaggregator.main_activity;

import android.graphics.drawable.Drawable;
import android.view.View;

import java.security.PrivateKey;

public class MenuEntry {

    private final Drawable mIcon;
    private final String mTitle;
    private final View.OnClickListener mListener;

    public MenuEntry(Drawable icon, String title, View.OnClickListener listener) {
        mIcon = icon;
        mTitle = title;
        mListener = listener;
    }
    public String getTitle() {
        return mTitle;
    }

    public Drawable getIcon() {
        return mIcon;
    }

    public View.OnClickListener getListener() {
        return mListener;
    }
}
