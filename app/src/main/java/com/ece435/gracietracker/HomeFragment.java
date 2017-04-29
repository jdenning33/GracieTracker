package com.ece435.gracietracker;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class HomeFragment extends Fragment implements OnClickListener {

    private String email = "";
    private String uid = "";


    // TODO: Rename and change types of parameters
    private String courseTitle;
    private String courseDescription;

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

        // Load the user profile
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();
            email = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getToken() instead.
            uid = user.getUid();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Capture the layout's TextView and set the email as its text
        TextView nameView = (TextView) view.findViewById(R.id.UserNameView);
        nameView.setText( email );

        TextView currentBeltText = (TextView) view.findViewById(R.id.CurrentBeltText);
        currentBeltText.setText( getCurrentBelt(uid) );

        TextView nextBeltText = (TextView) view.findViewById(R.id.NextBeltText);
        nextBeltText.setText( getNextBelt(uid) );

        TextView coursesNeededText = (TextView) view.findViewById(R.id.CoursesNeededText);
        coursesNeededText.setText( getCoursesNeeded(uid) );


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


    private String getName(String userID) {
        return "Droid Guy";
    }

    private String getCurrentBelt(String userID) {
        return "Yellow";
    }

    private String getNextBelt(String userID) {
        return "Blue";
    }

    private String getCoursesNeeded(String userID) {
        return "14";
    }

}
