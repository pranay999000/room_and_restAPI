package com.example.roomandrestapi.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomandrestapi.R;

import java.util.List;

public class TextAdapter extends RecyclerView.Adapter<TextAdapter.ViewHolder> {
    private List<String> list;

    public TextAdapter(List<String> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_text, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.text.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView text;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            text = itemView.findViewById(R.id.text);
        }
    }
}
