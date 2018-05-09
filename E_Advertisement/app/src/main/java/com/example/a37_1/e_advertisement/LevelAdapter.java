package com.example.a37_1.e_advertisement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.a37_1.e_advertisement.model.LevelModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vova0199 on 08.05.2018.
 */

public class LevelAdapter extends ArrayAdapter<LevelModel> {

    ArrayList<LevelModel> contactList;
    Context context;
    private LayoutInflater mInflater;

    // Constructors
    public LevelAdapter(Context context, ArrayList<LevelModel> objects) {
        super(context, 0, objects);
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        contactList = objects;
    }

    @Override
    public LevelModel getItem(int position) {
        return contactList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;
        if (convertView == null) {
            View view = mInflater.inflate(R.layout.layout_row_view, parent, false);
            vh = ViewHolder.create((RelativeLayout) view);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        LevelModel item = getItem(position);

        vh.textViewName.setText(item.getLevel());
        return vh.rootView;
    }

    private static class ViewHolder {
        public final RelativeLayout rootView;
        public final TextView textViewName;

        private ViewHolder(RelativeLayout rootView, TextView textViewName) {
            this.rootView = rootView;
            this.textViewName = textViewName;
        }

        public static ViewHolder create(RelativeLayout rootView) {
            TextView textViewEmail = rootView.findViewById(R.id.textViewName);
            return new ViewHolder(rootView,  textViewEmail);
        }

    }

}
