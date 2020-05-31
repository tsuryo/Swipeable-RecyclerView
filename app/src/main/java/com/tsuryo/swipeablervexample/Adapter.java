package com.tsuryo.swipeablervexample;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Created by Tsur Yohananov on 2020-05-07.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private final List<String> mList;

    public Adapter(List<String> list) {
        mList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.rv_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv.setText(mList.get(position));
        holder.tv1.setText(mList.get(position));
        holder.tv2.setText(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv, tv1, tv2;

        public ViewHolder(@NonNull View v) {
            super(v);
            tv = v.findViewById(R.id.tv);
            tv1 = v.findViewById(R.id.tv1);
            tv2 = v.findViewById(R.id.tv2);
        }
    }
}
