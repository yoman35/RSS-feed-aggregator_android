package yb.rssfeedaggregator.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class User implements Parcelable {

    String login;
    String password;
    String email;
    String token;
    List<Category> categories;
    List<Article> favorites;

    public User() {
        setDefault();
    }

    private void setDefault() {
        login = password = email = token = "";
        categories = new ArrayList<>();
        favorites = new ArrayList<>();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<Article> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<Article> favorites) {
        this.favorites = favorites;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(login);
        dest.writeString(password);
        dest.writeString(email);
        dest.writeString(token);
        dest.writeTypedList(categories);
        dest.writeList(favorites);
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }

    };

    public User(Parcel in) {
        login = in.readString();
        password = in.readString();
        email = in.readString();
        token = in.readString();
        in.readTypedList(categories, Category.CREATOR);
        in.readTypedList(favorites, Article.CREATOR);
    }
}
