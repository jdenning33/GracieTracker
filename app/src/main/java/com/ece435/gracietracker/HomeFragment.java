package com.ece435.gracietracker;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class HomeFragment extends Fragment implements OnClickListener {

    private HomeFragment.OnFragmentInteractionListener mListener;

    public HomeFragment() {
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
//        args.putString(COURSE_TITLE, courseTitle);
//        args.putString(COURSE_DESCRIPTION, courseDescription);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Load the user profile
        GracieUser gracieUser = Firebase.getGracieUser();

        // Capture the layout's TextView and set the email as its text
        TextView nameView = (TextView) view.findViewById(R.id.UserNameView);
        nameView.setText( gracieUser.preferredName );

        TextView currentBeltText = (TextView) view.findViewById(R.id.CurrentBeltText);
        currentBeltText.setText( GracieUser.getBeltColor(gracieUser.currentBelt) );

        TextView nextBeltText = (TextView) view.findViewById(R.id.NextBeltText);
        nextBeltText.setText( GracieUser.getBeltColor(gracieUser.currentBelt + 1) );

        TextView coursesNeededText = (TextView) view.findViewById(R.id.CoursesNeededText);
        coursesNeededText.setText( ""+gracieUser.getNumCoursesTilNext() );


        CourseListAdapterSnapshot courseAdapter = new CourseListAdapterSnapshot(getContext(), R.layout.layout_course_list_item, Course.courseArray);
        GridView gridView = (GridView) view.findViewById(R.id.CourseGridView);
        int columns = 3;
        gridView.setNumColumns(columns);
        gridView.setAdapter(courseAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                mListener.goToCourseView(position);
            }
        });

        ((Button) view.findViewById(R.id.CoursesButton)).setOnClickListener(this);
        ((Button) view.findViewById(R.id.CalendarButton)).setOnClickListener(this);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof HomeFragment.OnFragmentInteractionListener) {
            mListener = (HomeFragment.OnFragmentInteractionListener) context;
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
    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     */
    public interface OnFragmentInteractionListener {
        void goToCoursesFragment();
        void goToCalendarFragment();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.CoursesButton:
                mListener.goToCoursesFragment();
                break;
            case R.id.CalendarButton:
                mListener.goToCalendarFragment();
                break;
        }
    }
}
