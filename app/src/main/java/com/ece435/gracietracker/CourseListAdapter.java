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
            v = vi.inflate(R.layout.layout_course_list_item, null);
        }

        final CheckBox cb0 = (CheckBox) v.findViewById(R.id.completed0);
        final CheckBox cb1 = (CheckBox) v.findViewById(R.id.completed1);
        final CheckBox cb2 = (CheckBox) v.findViewById(R.id.completed2);

        Course course = getItem(position);

        final int coursenum = course.getNumber();
        final GracieUser curUser = Firebase.getGracieUser();

        cb0.setTag(course);
        cb0.setChecked(curUser.didCompleteCourse(coursenum, 0));
        cb0.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                CheckBox cb = (CheckBox) v;
                Course course = (Course) cb.getTag();
                curUser.toggleCompletedCourse(coursenum, 0);

                if(!curUser.didCompleteCourse(coursenum, 0) || !curUser.didCompleteCourse(coursenum, 1)){
                    cb2.setEnabled(false);
                } else {
                    cb2.setEnabled(true);
                }
            }
        });
        cb1.setTag(course);
        cb1.setChecked(curUser.didCompleteCourse(coursenum, 1));
        cb1.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                CheckBox cb = (CheckBox) v;
                Course course = (Course) cb.getTag();
                curUser.toggleCompletedCourse(coursenum, 1);

                if(!curUser.didCompleteCourse(coursenum, 0) || !curUser.didCompleteCourse(coursenum, 1)){
                    cb2.setEnabled(false);
                } else {
                    cb2.setEnabled(true);
                }
            }
        });
        cb2.setTag(course);
        cb2.setChecked(curUser.didCompleteCourse(coursenum, 2));
        cb2.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                CheckBox cb = (CheckBox) v;
                Course course = (Course) cb.getTag();
                curUser.toggleCompletedCourse(coursenum, 2);

                if(curUser.didCompleteCourse(coursenum, 2) ){
                    cb0.setEnabled(false);
                    cb1.setEnabled(false);
                } else {
                    cb0.setEnabled(true);
                    cb1.setEnabled(true);
                }
            }
        });
        if(!curUser.didCompleteCourse(coursenum, 0) || !curUser.didCompleteCourse(coursenum, 1)){
            cb2.setEnabled(false);
        } else {
            cb2.setEnabled(true);
        }
        if(curUser.didCompleteCourse(coursenum, 2)){
            cb0.setEnabled(false);
            cb1.setEnabled(false);
        }else{
            cb0.setEnabled(true);
            cb1.setEnabled(true);
        }

        TextView tt1 = (TextView) v.findViewById(R.id.courseNumberView);
        TextView tt2 = (TextView) v.findViewById(R.id.coursePrimarySkill);
        TextView tt3 = (TextView) v.findViewById(R.id.courseSecondarySkill);

        if (tt1 != null) {
            tt1.setText(""+course.getNumber());
        }
        if (tt2 != null) {
            tt2.setText(course.getPrimaryTechnique());
        }
        if (tt3 != null) {
            String extra = "";
            if(course.getAllTechniques().size() > 2) extra = " ...";
            tt3.setText(course.getSecondaryTechnique() + extra);
        }

        return v;
    }

}

