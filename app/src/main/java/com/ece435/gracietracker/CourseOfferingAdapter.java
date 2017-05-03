package com.ece435.gracietracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.google.api.client.util.DateTime;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by jdenn on 5/3/2017.
 */

public class CourseOfferingAdapter extends ArrayAdapter<CourseOffering> {

    public CourseOfferingAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public CourseOfferingAdapter(Context context, int resource, List<CourseOffering> items) {
        super(context, resource, items);
    }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.layout_course_offering_item, null);
        }

        CourseOffering courseOffering = getItem(position);

//            final SimpleDateFormat format = new SimpleDateFormat("dd-yy h:mm a");
            final SimpleDateFormat format = new SimpleDateFormat("h:mm a");
        final int coursenum = courseOffering.courseNum;
//        final Course course = Course.courseArray.get(coursenum+1);

        TextView tt1 = (TextView) v.findViewById(R.id.CourseTitleText);
        TextView tt2 = (TextView) v.findViewById(R.id.CourseTimeText);

        tt1.setText(courseOffering.courseDescription);
        tt2.setText(String.format("%s", format.format(courseOffering.startTime.getValue())));

        return v;
    }

}

