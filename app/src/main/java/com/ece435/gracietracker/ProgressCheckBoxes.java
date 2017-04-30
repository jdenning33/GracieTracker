package com.ece435.gracietracker;

import android.view.View;
import android.widget.CheckBox;

/**
 * Created by jdenn on 4/30/2017.
 */

public class ProgressCheckBoxes {
    Course course;
    CheckBox cb0, cb1, cb2;
    public ProgressCheckBoxes(Course course, CheckBox cb0, CheckBox cb1, CheckBox cb2){
        this.course = course;
        this.cb0 = cb0;
        this.cb1 = cb1;
        this.cb2 = cb2;
    }

    public void initializeListeners(){
        cb0.setTag(course);
        cb0.setChecked(course.getDidComplete(0));
        cb0.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                CheckBox cb = (CheckBox) v;
                Course course = (Course) cb.getTag();
                course.toggleComplete(0);

                if(!course.getDidComplete(0) || !course.getDidComplete(1)){
                    cb2.setEnabled(false);
                } else {
                    cb2.setEnabled(true);
                }
            }
        });
        cb1.setTag(course);
        cb1.setChecked(course.getDidComplete(1));
        cb1.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                CheckBox cb = (CheckBox) v;
                Course course = (Course) cb.getTag();
                course.toggleComplete(1);

                if(!course.getDidComplete(0) || !course.getDidComplete(1)){
                    cb2.setEnabled(false);
                } else {
                    cb2.setEnabled(true);
                }
            }
        });
        cb2.setTag(course);
        if(!course.getDidComplete(0) || !course.getDidComplete(1)){
            cb0.setEnabled(true);
            cb1.setEnabled(true);
            cb2.setEnabled(false);
        } else {
            cb0.setEnabled(false);
            cb1.setEnabled(false);
            cb2.setEnabled(true);
        }
        cb2.setChecked(course.getDidComplete(2));
        cb2.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                CheckBox cb = (CheckBox) v;
                Course course = (Course) cb.getTag();
                course.toggleComplete(2);

                if(course.getDidComplete(2) ){
                    cb0.setEnabled(false);
                    cb1.setEnabled(false);
                } else {
                    cb0.setEnabled(true);
                    cb1.setEnabled(true);
                }
            }
        });
    }
}
