package com.example.andrewlow.iheart2;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter{

    Context context;
    private ArrayList<String> item;

    public CustomAdapter(Activity context, ArrayList item){
            //super(context,R.layout.custom_text,item);
            this.context = context;
            this.item = item;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;

        final View results;

        if (convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.custom_text,parent,false);
            viewHolder.txtName = convertView.findViewById(R.id.text);
            viewHolder.icon = convertView.findViewById(R.id.imageIcon);

            results = convertView;

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
            results = convertView;
        }

        viewHolder.txtName.setText(item.get(position));
        //viewHolder.icon.setImageResource(images[position]);

        return results;
    }

    private static class ViewHolder {
        TextView txtName;
        ImageView icon;
    }
}
