package yb.rssfeedaggregator.main_activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import info.hoang8f.widget.FButton;
import yb.rssfeedaggregator.R;
import yb.rssfeedaggregator.models.User;

public class MenuFragment extends Fragment {

    @SuppressWarnings("unused")
    private static final String LOG = MenuFragment.class.getSimpleName();

    private static final int
            LAYOUT = R.layout.fragment_menu,
            RECYCLER = R.id.recycler_view,
            CHANGE_USER = R.id.change_user,
            CONNECT_BUTTON = R.id.login_button,
            ICON_HOME = R.drawable.ic_home_black_24dp,
            ICON_ADMIN = R.drawable.ic_library_books_black_24dp,
            ICON_HELP = R.drawable.ic_help_black_24dp,
            STR_HOME = R.string.home,
            STR_ADMIN = R.string.admin,
            STR_HELP = R.string.help,

    INTENT_USER = R.string.intent_user;
    private MainActivity mActivity;
    private RecyclerView mList;
    private TextView mChangeUser;
    private FButton mConnectButton;
    private User mUser = null;

    public MenuFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(LAYOUT, container, false);
        initVars(v);
        setListView();
        setListViewUserUnknown();
        return v;
    }

    private void initVars(View v) {
        Bundle arguments = getArguments();
        if (arguments != null)
            mUser = arguments.getParcelable(getString(INTENT_USER));
        mActivity = (MainActivity) getActivity();
        mList = (RecyclerView) v.findViewById(RECYCLER);
        mChangeUser = (TextView) v.findViewById(CHANGE_USER);
        mConnectButton = (FButton) v.findViewById(CONNECT_BUTTON);
    }

    private void setListView() {
        if (mUser == null) setListViewUserUnknown();
        else setListViewUserKnown();
    }

    private void setListViewUserUnknown() {
        int orientation = LinearLayoutManager.VERTICAL;
        mList.setLayoutManager(new LinearLayoutManager(mActivity, orientation, false));
        mList.setAdapter(new MenuAdapter(getDataUserUnknown()));
    }

    private void setListViewUserKnown() {
        int orientation = LinearLayoutManager.VERTICAL;
        mList.setLayoutManager(new LinearLayoutManager(mActivity, orientation, false));
        mList.setAdapter(new MenuAdapter(getDataUserKnown()));
    }

    private List<MenuEntry> getDataUserUnknown() {
        List<MenuEntry> data = new ArrayList<>();
        data.add(new MenuEntry(getDrawable(ICON_HOME), getString(STR_HOME), getHomeListener()));
        data.add(new MenuEntry(getDrawable(ICON_HELP), getString(STR_HELP), getHelpListener()));
        return data;
    }

    private List<MenuEntry> getDataUserKnown() {
        List<MenuEntry> data = new ArrayList<>();
        data.add(new MenuEntry(getDrawable(ICON_HOME), getString(STR_HOME), getHomeListener()));
        data.add(new MenuEntry(getDrawable(ICON_ADMIN), getString(STR_ADMIN), getAdminListener()));
        data.add(new MenuEntry(getDrawable(ICON_HELP), getString(STR_HELP), getHelpListener()));
        return data;
    }

    private Drawable getDrawable(int id) {
        return ContextCompat.getDrawable(mActivity, id);
    }

    public void switchToLogged() {
        mChangeUser.setVisibility(View.VISIBLE);
        mConnectButton.setVisibility(View.GONE);
    }

    public void switchToUnknownUser() {
        mChangeUser.setVisibility(View.GONE);
        mConnectButton.setVisibility(View.VISIBLE);
    }

    private View.OnClickListener getHomeListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.closeDrawer();
                mActivity.setContentFragment(mUser, new MainFragment());
            }
        };
    }

    private View.OnClickListener getAdminListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.closeDrawer();
                mActivity.setContentFragment(mUser, new AdminCategoryFragment());
            }
        };
    }

    private View.OnClickListener getHelpListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.closeDrawer();
                mActivity.setContentFragmentToHelp();
            }
        };
    }


}
