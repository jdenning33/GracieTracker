package com.ece435.gracietracker;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class CourseFragment extends Fragment {

    Course course;
    private OnFragmentInteractionListener mListener;

    public CourseFragment() { }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     */
    public static CourseFragment newInstance(int courseID) {
        CourseFragment fragment = new CourseFragment();

        Bundle args = new Bundle();
        args.putInt(CourseListFragment.COURSE_ID, courseID);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            int courseID = getArguments().getInt(CourseListFragment.COURSE_ID);
            course = Course.courseArray.get(courseID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_course, container, false);

        // Capture the layout's TextView and set the string as its text
        TextView courseNumberView = (TextView) view.findViewById(R.id.courseNumberView);
        TextView primaryTechniqueView = (TextView) view.findViewById(R.id.primaryTechniqueView);
        TextView secondaryTechniqueView = (TextView) view.findViewById(R.id.secondaryTechniqueView);

        Button primaryTechniqueLinkView = (Button) view.findViewById(R.id.primaryTechniqueLinkView);
        Button secondaryTechniqueLinkView = (Button) view.findViewById(R.id.secondaryTechniqueLinkView);

        CheckBox cb0 = (CheckBox) view.findViewById(R.id.checkBox0);
        cb0.setTag(course);
        CheckBox cb1 = (CheckBox) view.findViewById(R.id.checkBox1);
        cb1.setTag(course);
        CheckBox cb2 = (CheckBox) view.findViewById(R.id.checkBox2);
        cb2.setTag(course);

        cb0.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                CheckBox cb = (CheckBox) v;
                Course course = (Course) cb.getTag();
                course.toggleComplete(0);
            }
        });
        cb1.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                CheckBox cb = (CheckBox) v;
                Course course = (Course) cb.getTag();
                course.toggleComplete(1);
            }
        });
//            cb2.setOnClickListener( new View.OnClickListener() {
//                public void onClick(View v) {
//                    CheckBox cb = (CheckBox) v;
//                    Course course = (Course) cb.getTag();
//                    course.toggleComplete(2);
//                }
//            });

        int courseNumber = course.getNumber();
        String primaryTechnique = course.getPrimaryTechnique();
        String secondaryTechnique = course.getSecondaryTechnique();
        String primaryTechniqueLink = course.getPrimaryTechniqueLink();
        String secondaryTechniqueLink = course.getSecondaryTechniqueLink();


        primaryTechniqueLinkView.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                Button link = (Button) v;

                String address = course.getPrimaryTechniqueLink();
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(address));
                startActivity(i);
            }
        });
        secondaryTechniqueLinkView.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                Button link = (Button) v;

                String address = (String) link.getText();
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(address));
                startActivity(i);
            }
        });

        courseNumberView.setText(""+courseNumber);
        primaryTechniqueView.setText(primaryTechnique);
        secondaryTechniqueView.setText(secondaryTechnique);
        cb0.setChecked(course.getDidComplete(0));
        cb1.setChecked(course.getDidComplete(1));
        cb2.setChecked(course.getDidComplete(2));

        return view;
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     */
    public interface OnFragmentInteractionListener {
    }
}
