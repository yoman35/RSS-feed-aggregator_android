package yb.rssfeedaggregator.models;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.List;

public class Article implements Parcelable {

    String title;
    String url;
    String author;
    Date publishedDate;
    String shortText;
    String article;
    List<Bitmap> images;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getShortText() {
        return shortText;
    }

    public void setShortText(String shortText) {
        this.shortText = shortText;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public List<Bitmap> getImages() {
        return images;
    }

    public void setImages(List<Bitmap> images) {
        this.images = images;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(url);
        dest.writeString(author);
        dest.writeSerializable(publishedDate);
        dest.writeString(shortText);
        dest.writeString(article);
        Parcelable[] parcelable = images.toArray(new Parcelable[images.size()]);
        dest.writeParcelableArray(parcelable, flags);
    }

    public static final Parcelable.Creator<Article> CREATOR = new Parcelable.Creator<Article>() {
        @Override
        public Article createFromParcel(Parcel source) {
            return new Article(source);
        }

        @Override
        public Article[] newArray(int size) {
            return new Article[size];
        }

    };

    public Article(Parcel in) {
        title = in.readString();
        url = in.readString();
        author = in.readString();
        publishedDate = (Date) in.readSerializable();
        shortText = in.readString();
        article = in.readString();
        Parcelable[] parcelable = in.readParcelableArray(Bitmap.class.getClassLoader());
        images.clear();
        for (Parcelable p : parcelable) {
            images.add((Bitmap) p);
        }
    }
}
