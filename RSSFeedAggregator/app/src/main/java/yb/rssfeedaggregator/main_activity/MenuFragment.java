package yb.rssfeedaggregator.main_activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import yb.rssfeedaggregator.R;

public class MenuFragment extends Fragment {

    private RecyclerView mList;

    public MenuFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_menu, container, false);
        mList = (RecyclerView) v.findViewById(R.id.recycler_view);
        setListView();
        return v;
    }

    private void setListView() {
        int orientation = LinearLayoutManager.VERTICAL;
        mList.setLayoutManager(new LinearLayoutManager(getActivity(), orientation, false));
        mList.setAdapter(new MenuAdapter(getData()));
    }

    private List<MenuEntry> getData() {
        List<MenuEntry> data = new ArrayList<>();
        int home_icon = R.drawable.ic_home_black_24dp;
        int admin_icon = R.drawable.ic_library_books_black_24dp;
        int help_icon = R.drawable.ic_help_black_24dp;
        data.add(new MenuEntry(getDrawable(home_icon), getString(R.string.home)));
        data.add(new MenuEntry(getDrawable(admin_icon), getString(R.string.admin)));
        data.add(new MenuEntry(getDrawable(help_icon), getString(R.string.help)));
        return data;
    }

    private Drawable getDrawable(int id) {
        return ContextCompat.getDrawable(getActivity(), id);
    }

}
