package com.ece435.gracietracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class CourseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        int courseID = intent.getIntExtra(CoursesActivity.COURSE_ID, 0);

        // Capture the layout's TextView and set the string as its text
        TextView courseIDview = (TextView) findViewById(R.id.courseID);

        Course.courseCursor.moveToPosition(courseID);
        String primaryTechnique = Course.courseCursor.getString(1);

        courseIDview.setText(""+(primaryTechnique));

    }
}
