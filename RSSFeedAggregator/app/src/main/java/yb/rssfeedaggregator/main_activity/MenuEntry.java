package yb.rssfeedaggregator.main_activity;

import android.graphics.drawable.Drawable;

public class MenuEntry {

    private final Drawable mIcon;
    private final String mTitle;

    public MenuEntry(Drawable icon, String title) {
        mIcon = icon;
        mTitle = title;
    }
    public String getTitle() {
        return mTitle;
    }

    public Drawable getIcon() {
        return mIcon;
    }
}
