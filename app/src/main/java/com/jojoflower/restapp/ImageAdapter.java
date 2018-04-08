package com.jojoflower.restapp;

import android.content.Context;
import android.database.DataSetObserver;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
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
        ImageView im;
        if(view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.imageadapter, viewGroup, false);
        }
        tv = view.findViewById(R.id.textViewList);
        im = view.findViewById(R.id.imageViewList);

        tv.setText(image.getName());
        im.setImageBitmap(image.getBitmap());

        return view;
    }
}
