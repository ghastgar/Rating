package com.curs.pau.jsoup;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * Created by pau on 04/02/15.
 */
public class RedditAdapter extends ArrayAdapter<RedditThing> {
    public RedditAdapter(Context context, List<RedditThing> data) {
        super(context, R.layout.adapter_reddit, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View row = inflater.inflate(R.layout.adapter_reddit, parent, false);

        TextView textView = (TextView) row.findViewById(R.id.textView);
        ImageView imageView = (ImageView) row.findViewById(R.id.imageView);

        RedditThing item = getItem(position);
        textView.setText(item.title);
        if (item.img != null) {
            imageView.setImageBitmap(item.img);
        }
        return row;
    }
}