package com.mirea.mobiledev;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class CustomAdapter extends ArrayAdapter {
    private String[] text;
    private Integer[] colors;

    private Context context;

    public CustomAdapter(Context context, String[] text, Integer[] colors) {
        super(context, R.layout.block, text);
        this.text = text;
        this.colors = colors;
        this.context=context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.block, parent, false);

        TextView textViewCountry = rowView.findViewById(R.id.text);
        ImageView imageFlag = rowView.findViewById(R.id.image);
        textViewCountry.setText(text[position]);
        imageFlag.setBackgroundColor(colors[position]);
        return rowView;
    }
}