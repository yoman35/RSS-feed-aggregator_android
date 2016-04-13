package yb.rssfeedaggregator.main_activity;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import yb.rssfeedaggregator.R;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {

    private static final int
            LAYOUT = R.layout.menu_item;

    private final List<MenuEntry> mData;

    public MenuAdapter(List<MenuEntry> data) {
        mData = data;
    }

    @Override
    public MenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(LAYOUT, parent, false);
        return new MenuViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MenuViewHolder holder, int position) {
        MenuEntry current = mData.get(position);
        holder.mRoot.setOnClickListener(current.getListener());
        holder.mIcon.setImageDrawable(current.getIcon());
        holder.mTitle.setText(current.getTitle());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder {

        private static final int
                ROOT_VIEW = R.id.root_view,
                ICON = R.id.icon,
                TITLE = R.id.title;

        View mRoot;
        ImageView mIcon;
        TextView mTitle;

        public MenuViewHolder(View v) {
            super(v);
            mRoot = v.findViewById(ROOT_VIEW);
            mIcon = (ImageView) v.findViewById(ICON);
            mTitle = (TextView) v.findViewById(TITLE);
        }
    }
}
