package com.mirea.mobiledev;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;


public class CustomRecyclerAdapter extends RecyclerView.Adapter<CustomRecyclerAdapter.ViewHolder>  {


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView textView;
        private final ImageView imageView;
        public ViewHolder(View view) {
            super(view);

            textView =  view.findViewById(R.id.text);
            imageView = view.findViewById(R.id.image);
            view.setOnClickListener(this);
        }
        public TextView getTextView() {
            return textView;
        }
        public ImageView getImageView() {
            return imageView;
        }

        @Override
        public void onClick(View v) {
            if (clickListener != null) clickListener.onClick(v, getAdapterPosition());
        }
    }

    private String[] text;
    private Integer[] colors;
    private ItemClickListener clickListener;
    public CustomRecyclerAdapter(String[] text, Integer[] colors) {
        this.text = text;
        this.colors = colors;
    }

    @NonNull
    @Override
    public CustomRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.block, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomRecyclerAdapter.ViewHolder holder, int position) {
        holder.getTextView().setText(text[position]);
        holder.getImageView().setBackgroundColor(colors[position]);
    }

    @Override
    public int getItemCount() {
        return text.length;
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }
}