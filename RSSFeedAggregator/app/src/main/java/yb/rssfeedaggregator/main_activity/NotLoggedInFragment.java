package yb.rssfeedaggregator.main_activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import yb.rssfeedaggregator.R;

public class NotLoggedInFragment extends Fragment {

    public NotLoggedInFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_not_logged_in, container, false);
    }

}
