package com.ece435.gracietracker;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements HomeFragment.OnFragmentInteractionListener,
CalendarFragment.OnFragmentInteractionListener,
CourseListFragment.OnFragmentInteractionListener,
CourseFragment.OnFragmentInteractionListener{

    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set the toolbar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);



        // Create a fragment manager so that we can initialize the MainFragment
        fragmentManager = getSupportFragmentManager();
        goToHomeFragment();
    }
    public void goToLoginView(View view) {
        goToLoginViewHelp();
    }
    public void goToLoginViewHelp() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }


    ///////////////////////////////////////////////////////////////////////////////////////////
    ///     Action Bar Initialization
    ///////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...
                return true;

            case R.id.sign_out:
                FirebaseAuth.getInstance().signOut();
                goToLoginViewHelp();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.appbar, menu);
        return true;
    }


    public void goToHomeFragment() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        HomeFragment userHomeFragment = HomeFragment.newInstance();
        fragmentTransaction.replace(R.id.MainFragment, userHomeFragment);
        fragmentTransaction.commit();
    }
    // HomeFragment interface
    @Override
    public void goToCoursesFragment() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        CourseListFragment courseListFragment = CourseListFragment.newInstance();
        fragmentTransaction.replace(R.id.MainFragment, courseListFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
    @Override
    public void goToCalendarFragment() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        CalendarFragment calendarFragment = CalendarFragment.newInstance();
        fragmentTransaction.replace(R.id.MainFragment, calendarFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void goToCourseView(int coursePrimary) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        CourseFragment courseFragment = CourseFragment.newInstance(coursePrimary);
        fragmentTransaction.replace(R.id.MainFragment, courseFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}