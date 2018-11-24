package ru.geekbrains.gviewer.view;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import ru.geekbrains.gviewer.R;

public class MainViewAdapter extends RecyclerView.Adapter<MainViewAdapter.MainViewHolder> {
    private List<String> itemsList;

    public static class MainViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        public MainViewHolder(LinearLayout ll) {
            super(ll);
            textView = ll.findViewById(R.id.rv_text_field);
        }
    }

    public MainViewAdapter(List<String> data) {
        itemsList = data;
    }

    public List<String> getItemsList() {
        return itemsList;
    }

    public void addItems(List<String> data) {
        itemsList.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayout ll = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        MainViewHolder vh = new MainViewHolder(ll);
        return vh;
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {
        holder.textView.setText(itemsList.get(position));
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }
}
