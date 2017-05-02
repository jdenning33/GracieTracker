package com.ece435.gracietracker;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TabHost;

import java.util.ArrayList;

public class CourseListFragment extends Fragment {
    public static final String COURSE_ID = "com.ece435.gracietracker.courseID";

    private OnFragmentInteractionListener mListener;

    public CourseListFragment() { }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     */
    public static CourseListFragment newInstance() {
        CourseListFragment fragment = new CourseListFragment();
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
        View view = inflater.inflate(R.layout.fragment_course_list, container, false);

        // Find the tab host
        TabHost host = (TabHost) view.findViewById(R.id.CourseTypeTab);
        host.setup();

        // Inflate Tab1 for the tab layout
        FrameLayout tab1 = (FrameLayout) view.findViewById(R.id.course_list);
        View view1 = inflater.inflate(R.layout.tab_course_list, tab1, true);
        CourseListAdapter courseAdapter = new CourseListAdapter(getContext(), R.layout.layout_course_list_item, Course.courseArray);
        ListView listView1 = (ListView) view1.findViewById(R.id.CourseGridView);
        listView1.setAdapter(courseAdapter);

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mListener.goToCourseView(position);
            }
        });

        // Inflate Tab2 for the tab layout
        ArrayList<Integer> ints = new ArrayList<Integer>();
        for(int i=1; i<15; i++) ints.add(i);
        FrameLayout tab2 = (FrameLayout) view.findViewById(R.id.reflex_list);
        View view2 = inflater.inflate(R.layout.tab_reflex_list, tab2, true);
        ReflexListAdapter reflexAdapter = new ReflexListAdapter(getContext(), R.layout.layout_reflex_list_item, ints);
        ListView listView2 = (ListView) view2.findViewById(R.id.ReflexCoursesList);
        listView2.setAdapter(reflexAdapter);

        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                mListener.goToCourseView(position);
            }
        });


        //Add Tab 1 to the host
        TabHost.TabSpec spec = host.newTabSpec("Skill Courses");
        spec.setContent(R.id.course_list);
        spec.setIndicator("Skill Courses");
        host.addTab(spec);

        //Tab 2
        spec = host.newTabSpec("Reflex Development");
        spec.setContent(R.id.reflex_list);
        spec.setIndicator("Reflex Development");
        host.addTab(spec);

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
        Firebase.commitGracieUserToDB();
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
        void goToCourseView(int coursePrimary);
    }
}
