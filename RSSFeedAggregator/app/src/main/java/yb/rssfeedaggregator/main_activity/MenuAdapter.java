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

    private final List<MenuEntry> mData;

    public MenuAdapter(List<MenuEntry> data) {
        mData = data;
    }

    @Override
    public MenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layout = R.layout.menu_item;
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new MenuViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MenuViewHolder holder, int position) {
        MenuEntry current = mData.get(position);
        holder.mIcon.setImageDrawable(current.getIcon());
        holder.mTitle.setText(current.getTitle());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder {

        ImageView mIcon;
        TextView mTitle;

        public MenuViewHolder(View v) {
            super(v);
            mIcon = (ImageView) v.findViewById(R.id.icon);
            mTitle = (TextView) v.findViewById(R.id.title);
        }
    }
}
