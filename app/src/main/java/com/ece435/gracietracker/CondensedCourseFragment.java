package com.ece435.gracietracker;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.ContentFrameLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CondensedCourseFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CondensedCourseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CondensedCourseFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String COURSE_TITLE = "com.ece435.gracietracker.courseTitle";
    private static final String COURSE_DESCRIPTION = "com.ece435.gracietracker.courseDescription";

    // TODO: Rename and change types of parameters
    private String courseTitle;
    private String courseDescription;

    private OnFragmentInteractionListener mListener;

    public CondensedCourseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     */
    // TODO: Rename and change types and number of parameters
    public static CondensedCourseFragment newInstance(String courseTitle, String courseDescription) {
        CondensedCourseFragment fragment = new CondensedCourseFragment();
        Bundle args = new Bundle();
        args.putString(COURSE_TITLE, courseTitle);
        args.putString(COURSE_DESCRIPTION, courseDescription);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            courseTitle = getArguments().getString(COURSE_TITLE);
            courseDescription = getArguments().getString(COURSE_DESCRIPTION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_condensed_course, container, false);


        TextView courseTitleView = (TextView) view.findViewById(R.id.courseTitle);
        courseTitleView.setText( courseTitle );

        TextView courseDescriptionView = (TextView) view.findViewById(R.id.courseDescription);
        courseDescriptionView.setText( courseDescription );


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
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
