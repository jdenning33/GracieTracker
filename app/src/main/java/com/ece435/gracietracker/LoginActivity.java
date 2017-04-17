package com.ece435.gracietracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    public static final String USER_NAME = "com.ece435.gracietracker.USER_NAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public String getUserName() {
        EditText editText = (EditText) findViewById(R.id.editText2);
        String userName = editText.getText().toString();
        if(userName == null) return "Guest";
        return userName;
    }

    public void goToUserHomeView(View view) {
        Intent intent = new Intent(this, UserHomeActivity.class);
        String userName = getUserName();
        intent.putExtra(USER_NAME, userName);
        startActivity(intent);
    }
}
