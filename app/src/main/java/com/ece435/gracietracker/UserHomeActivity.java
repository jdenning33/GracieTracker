package com.ece435.gracietracker;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class UserHomeActivity extends AppCompatActivity implements CondensedCourseFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);


        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String userID = intent.getStringExtra(LoginActivity.USER_NAME);

        // Capture the layout's TextView and set the string as its text
        TextView nameView = (TextView) findViewById(R.id.userNameView);
        nameView.setText( getName(userID) );

        TextView currentBeltView = (TextView) findViewById(R.id.currentBeltView);
        currentBeltView.setText( getCurrentBelt(userID) );

        TextView nextBeltView = (TextView) findViewById(R.id.nextBeltView);
        nextBeltView.setText( getNextBelt(userID) );

        TextView coursesNeededView = (TextView) findViewById(R.id.coursesNeededView);
        coursesNeededView.setText( getCoursesNeeded(userID) );


        // Create a fragment manager so that we can initialize the 'CondensedCourseFragment'
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        CondensedCourseFragment suggestedCourseFragment =
                CondensedCourseFragment.newInstance("Trap and Roll Escape - Mount (GU 1)",
                        "Leg Hook Takedowns (GU 6)");
        fragmentTransaction.replace(R.id.SuggestedCourseFragment, suggestedCourseFragment);
        fragmentTransaction.commit();

    }

    public void goToCoursesView(View view) {
        Intent intent = new Intent(this, CoursesActivity.class);
//        String userName = getUserName();
//        intent.putExtra(USER_NAME, userName);
        startActivity(intent);
    }
    public void goToCalendarView(View view) {
        Intent intent = new Intent(this, CalendarActivity.class);
        startActivity(intent);
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


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
