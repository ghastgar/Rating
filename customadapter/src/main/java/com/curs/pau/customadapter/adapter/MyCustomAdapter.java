package com.curs.pau.customadapter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.curs.pau.customadapter.R;
import com.curs.pau.customadapter.model.Cervesa;

import java.util.List;

/**
 * Created by pau on 27/01/15.
 */
public class MyCustomAdapter extends ArrayAdapter {
    private List<Cervesa> mCerveses;
    private Context mContext;
    private int mResource;

    public MyCustomAdapter(Context context, List<Cervesa> data) {
        super(context, R.layout.custom_item, data);
        mContext = context;
        mCerveses = data;
        mResource = R.layout.custom_item;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater =
                (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = layoutInflater.inflate(mResource, parent, false);

        TextView name =  (TextView) rowView.findViewById(R.id.name);
        TextView rating =  (TextView) rowView.findViewById(R.id.rating);

        Cervesa bier = mCerveses.get(position);
        name.setText(bier.name);
        rating.setText(bier.rating);

        return rowView;
    }
}