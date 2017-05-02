package com.ece435.gracietracker;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jdenn on 5/1/2017.
 */

public class ReflexListAdapter extends ArrayAdapter<Integer> {

    public ReflexListAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public ReflexListAdapter(Context context, int resource, List<Integer> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View v = convertView;

        int belt = getItem(position);

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.layout_reflex_list_item, null);
        }

        final GracieUser gracieUser = Firebase.getGracieUser();

        TextView tt1 = (TextView) v.findViewById(R.id.BeltColorText);
        tt1.setText(""+GracieUser.getBeltColor(belt));

        GridView gridView = (GridView) v.findViewById(R.id.ReflexCheckboxList);
        ArrayList<Bundle> checkBoxBundles = new ArrayList<Bundle>();
        for(int i=0; i<12; i++){
            Bundle cb = new Bundle();
            cb.putInt("belt",belt);
            cb.putInt("num",i);
            checkBoxBundles.add(cb);
        }
        CheckBoxAdapter adapter = new CheckBoxAdapter(getContext(), R.layout.layout_check_box_item, checkBoxBundles);
        gridView.setNumColumns(checkBoxBundles.size());
        gridView.setAdapter(adapter);

        return v;
    }

    private class CheckBoxAdapter extends ArrayAdapter<Bundle> {

        public CheckBoxAdapter(Context context, int textViewResourceId) {
            super(context, textViewResourceId);
        }

        public CheckBoxAdapter(Context context, int resource, List<Bundle> items) {
            super(context, resource, items);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            View v = convertView;

            Bundle args = getItem(position);
            int belt = args.getInt("belt");
            int num = args.getInt("num");

            if (v == null) {
                LayoutInflater vi;
                vi = LayoutInflater.from(getContext());
                v = vi.inflate(R.layout.layout_check_box_item, null);
            }

            final GracieUser gracieUser = Firebase.getGracieUser();
            CheckBox cb = (CheckBox) v.findViewById(R.id.checkBox);
            cb.setTag(args);
            cb.setChecked(gracieUser.didCompleteReflex(belt, num));

            cb.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    CheckBox cb = (CheckBox) v;
                    Bundle args = (Bundle) cb.getTag();
                    int belt = args.getInt("belt");
                    int num = args.getInt("num");
                    gracieUser.toggleCompletedReflex(belt, num);
                }
            });


            return v;
        }
    }

}

