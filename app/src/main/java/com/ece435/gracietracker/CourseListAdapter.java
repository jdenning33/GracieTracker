package com.ece435.gracietracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

/**
 * Created by jdenn on 4/17/2017.
 */

public class CourseListAdapter extends ArrayAdapter<Course> {

    public CourseListAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public CourseListAdapter(Context context, int resource, List<Course> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.course_list_item, null);

            CheckBox cb0 = (CheckBox) v.findViewById(R.id.completed0);
            cb0.setOnClickListener( new View.OnClickListener() {
                public void onClick(View v) {
                    CheckBox cb = (CheckBox) v;
                    Course course = (Course) cb.getTag();
                    course.toggleComplete(0);
                }
            });
            CheckBox cb1 = (CheckBox) v.findViewById(R.id.completed1);
            cb1.setOnClickListener( new View.OnClickListener() {
                public void onClick(View v) {
                    CheckBox cb = (CheckBox) v;
                    Course course = (Course) cb.getTag();
                    course.toggleComplete(1);
                }
            });
//            CheckBox cb2 = (CheckBox) v.findViewById(R.id.completed2);
//            cb2.setOnClickListener( new View.OnClickListener() {
//                public void onClick(View v) {
//                    CheckBox cb = (CheckBox) v;
//                    Course course = (Course) cb.getTag();
//                    course.toggleComplete(2);
//                }
//            });
        }

        Course course = getItem(position);

        if (course != null) {
            TextView tt1 = (TextView) v.findViewById(R.id.courseNumberView);
            TextView tt2 = (TextView) v.findViewById(R.id.coursePrimarySkill);
            TextView tt3 = (TextView) v.findViewById(R.id.courseSecondarySkill);

            CheckBox cb0 = (CheckBox) v.findViewById(R.id.completed0);
            CheckBox cb1 = (CheckBox) v.findViewById(R.id.completed1);
//            CheckBox cb2 = (CheckBox) v.findViewById(R.id.completed2);

            if (tt1 != null) {
                tt1.setText(""+course.getNumber());
            }
            if (tt2 != null) {
                tt2.setText(course.getPrimaryTechnique());
            }
            if (tt3 != null) {
                tt3.setText(course.getSecondaryTechnique());
            }
            if (cb0 != null) {
                cb0.setTag(course);
                cb0.setChecked(course.getDidComplete(0));
            }
            if (cb1 != null) {
                cb1.setTag(course);
                cb1.setChecked(course.getDidComplete(1));
            }
//            if (cb2 != null) {
//                cb2.setTag(course);
//                cb2.setChecked(course.getDidComplete(2));
//            }
        }

        return v;
    }

}

