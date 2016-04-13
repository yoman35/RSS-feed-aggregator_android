package yb.rssfeedaggregator.main_activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import yb.rssfeedaggregator.R;

public class AdminCategoryFragment extends Fragment {

    @SuppressWarnings("unused")
    private static final String LOG = AdminCategoryFragment.class.getSimpleName();

    private static final int
            LAYOUT = R.layout.fragment_admin_category,
            TOOLBAR_TITLE = R.string.admin;

    private MainActivity mActivity;

    public AdminCategoryFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(LAYOUT, container, false);
        mActivity = (MainActivity) getActivity();
        mActivity.setToolbarTitle(getString(TOOLBAR_TITLE));
        return v;
    }

}
