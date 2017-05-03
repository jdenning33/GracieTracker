package com.ece435.gracietracker;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CalendarFragment2 extends Fragment implements CalendarApi.CalendarApiListener{

    private OnFragmentInteractionListener mListener;
    private CalendarApi calendarApi;


    public CalendarFragment2() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     */
    public static CalendarFragment2 newInstance() {
        CalendarFragment2 fragment = new CalendarFragment2();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
        calendarApi = new CalendarApi(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);

        CalendarView cView = (CalendarView) view.findViewById(R.id.CalendarView);
        cView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                java.util.Calendar eventCal = java.util.Calendar.getInstance();
                eventCal.set(year, month, dayOfMonth, 0, 0, 0);

                calendarApi.getResultsFromApi(eventCal);

            }
        });

        Calendar eventCal = Calendar.getInstance();
        eventCal.set(Calendar.HOUR_OF_DAY, 0);
        eventCal.set(Calendar.MINUTE, 0);
        calendarApi.getResultsFromApi(eventCal);


        return view;
    }

    public void updateCourseOfferings(List<Event> events){
        View v = getView();

        ArrayList<CourseOffering> offerings = new ArrayList<CourseOffering>();
        for (Event event : events) {
            DateTime start = event.getStart().getDateTime();
            if (start == null) {
                // All-day events don't have start times, so just use
                // the start date.
                start = event.getStart().getDate();
            }
            CourseOffering courseOffering = new CourseOffering();
            courseOffering.startTime = start;
            courseOffering.courseDescription = event.getSummary();
            offerings.add(courseOffering);
        }

        ListView offeringsView = (ListView) v.findViewById(R.id.CourseOfferingsList);
        CourseOfferingAdapter adapter = new CourseOfferingAdapter(getActivity(), R.layout.layout_course_offering_item, offerings);
        offeringsView.setAdapter(adapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("Fragment2", "onActivityResult");
        if(calendarApi == null) return;
        calendarApi.onActivityResult(requestCode, resultCode, data);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults){
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.e("Fragment2", "onRequestPermissionsResult");
        if(calendarApi == null) return;
        calendarApi.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
    @Override
    public void onCalendarResultReturns(List<Event> events) {
        Log.e("Fragment2", "onCalendarResultReturns");
        updateCourseOfferings(events);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     */
    public interface OnFragmentInteractionListener {
    }
}