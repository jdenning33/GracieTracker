package com.ece435.gracietracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.ece435.gracietracker.MESSAGE";
    public static final String USER_NAME = "com.ece435.gracietracker.NAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if( !isLoggedIn() ){
            Intent intent = new Intent(this, LoginActivity.class);
//            String userName = getUserName();
//            intent.putExtra(USER_NAME, userName);
            startActivity(intent);
        }
        else{
            setContentView(R.layout.activity_main);
        }
    }

    public boolean isLoggedIn() {
        return false;
    }
    public String getUserName() {
        return "Jason";
    }
}
