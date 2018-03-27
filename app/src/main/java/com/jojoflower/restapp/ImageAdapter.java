package com.jojoflower.restapp;

import android.content.Context;
import android.database.DataSetObserver;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lou-l on 27/03/2018.
 */

public class ImageAdapter extends ArrayAdapter<MyImage> {

    public ImageAdapter(@NonNull Context context, @NonNull ArrayList<MyImage> images) {
        super(context, R.layout.imageadapter, images);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MyImage image = getItem(i);
        TextView tv;
        if(view == null) {
            tv = new TextView(getContext());
        }
        else {
            tv = (TextView)view;
        }
        tv.setText(image.getName());
        return tv;
    }
}
