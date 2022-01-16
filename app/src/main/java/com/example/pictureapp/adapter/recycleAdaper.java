package com.example.pictureapp.adapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pictureapp.R;

import java.util.ArrayList;
import java.util.List;

public class recycleAdaper extends RecyclerView.Adapter<recycleAdaper.ItemViewHolder>{
    private List<CharSequence> dataset;
    private Context context;

    public recycleAdaper(List<CharSequence> dataset, Context context) {

        List<CharSequence> copy = new ArrayList<>();
        this.dataset = copy;
        for(CharSequence c : dataset) {
            copy.add(c);
        }

        this.context = context;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View adapterLayout = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        ItemViewHolder vh = new ItemViewHolder(adapterLayout);
        vh.setIsRecyclable(false);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        CharSequence item = dataset.get(position);
        holder.textView.setText(item);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        ItemViewHolder(View view) {
            super(view);
            textView = view.findViewById(R.id.item_title);

        }
    }

    public void clear() {
        int size = dataset.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                dataset.remove(0);
            }

        }
        notifyItemRangeRemoved(0, size);
    }
}
