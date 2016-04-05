package yb.rssfeedaggregator.models;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Site model
 *
 * Created by YomanHD on 25/03/2016.
 */
public class Site implements Parcelable {

    String name;
    String rootUrl;
    Bitmap logo;
    List<Article> articles;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(rootUrl);
        //TODO: logo
        dest.writeList(articles);
    }

    public static final Parcelable.Creator<Site> CREATOR = new Parcelable.Creator<Site>() {
        @Override
        public Site createFromParcel(Parcel source) {
            return new Site(source);
        }

        @Override
        public Site[] newArray(int size) {
            return new Site[size];
        }

    };

    public Site(Parcel in) {
        name = in.readString();
        rootUrl = in.readString();
        //TODO: logo
        in.readTypedList(articles, Article.CREATOR);
    }
}
