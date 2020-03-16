package com.example.ass_androidnangcao;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomView extends ArrayAdapter<Item> {
    Context context;
    int resourceId;
    ArrayList<Item> data = new ArrayList<>();

    public CustomView(Context context, int resourceId, ArrayList<Item> data) {
        super(context, resourceId, data);
        this.context = context;
        this.resourceId = resourceId;
        this.data = data;
    }

    static class RecordHolder {
        TextView txtTitle;
        ImageView imageItem;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        RecordHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(resourceId, parent, false);

            holder = new RecordHolder();
            holder.txtTitle = (TextView) row.findViewById(R.id.itemText);
            holder.imageItem = (ImageView) row.findViewById(R.id.itemImage);
            row.setTag(holder);
        } else {
            holder = (RecordHolder) row.getTag();
        }
        Item item = data.get(position);
        holder.txtTitle.setText(item.getTitle());
        holder.imageItem.setImageBitmap(item.getImage());
        return row;
    }
}
