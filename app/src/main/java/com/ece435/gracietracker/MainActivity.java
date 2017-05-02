package com.ece435.gracietracker;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DrawableUtils;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements HomeFragment.OnFragmentInteractionListener,
CalendarFragment.OnFragmentInteractionListener,
CourseListFragment.OnFragmentInteractionListener,
CourseFragment.OnFragmentInteractionListener,
Firebase.OnFireBaseInteractionListenerDB{

    FragmentManager fragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Firebase.initializeDB(this);

        // Set the toolbar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        // Create a fragment manager so that we can initializeAuth the MainFragment
        fragmentManager = getSupportFragmentManager();
//        goToHomeFragment();
    }
    public void goToLoginView(View view) {
        goToLoginViewHelp();
    }
    public void goToLoginViewHelp() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed(){
        Firebase.commitGracieUserToDB();
        super.onBackPressed();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    ///     Action Bar Initialization
    ///////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.action_settings:
                // GracieUser chose the "Settings" item, show the app settings UI...
                return true;

            case R.id.sign_out:
                FirebaseAuth.getInstance().signOut();
                Firebase.resetCurrentUser();
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

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(false);
            ab.setBackgroundDrawable(new ColorDrawable(
                    ContextCompat.getColor(this, R.color.colorSupportActionBar)));
            ab.show();
        }

        FragmentManager.OnBackStackChangedListener backStackListener =  new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {

                int position = getSupportFragmentManager().getBackStackEntryCount();
                if(position!=0){
                    FragmentManager.BackStackEntry backEntry=getSupportFragmentManager().getBackStackEntryAt(position-1);
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                }else{
                    getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                }
            }
        };
        getSupportFragmentManager().addOnBackStackChangedListener(backStackListener);

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


    //////////////////////////////////////////////////////////////////////////////////////
    ///     Firebase DB Listener
    /////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void onInitialGracieUserUpdate() {
        goToHomeFragment();
    }
    @Override
    public void reinitializeDatabase() {
        Firebase.initializeDB(this);
    }
}
