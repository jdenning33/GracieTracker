package com.ece435.gracietracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class CourseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        int courseID = intent.getIntExtra(CoursesActivity.COURSE_ID, 0);
        Course course = Course.courseArray.get(courseID);

        // Capture the layout's TextView and set the string as its text
        TextView courseNumberView = (TextView) findViewById(R.id.courseNumberView);
        TextView primaryTechniqueView = (TextView) findViewById(R.id.primaryTechniqueView);
        TextView secondaryTechniqueView = (TextView) findViewById(R.id.secondaryTechniqueView);

        CheckBox cb0 = (CheckBox) findViewById(R.id.checkBox0);
        cb0.setTag(course);
        CheckBox cb1 = (CheckBox) findViewById(R.id.checkBox1);
        cb1.setTag(course);
        CheckBox cb2 = (CheckBox) findViewById(R.id.checkBox2);
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

        courseNumberView.setText(""+courseNumber);
        primaryTechniqueView.setText(primaryTechnique);
        secondaryTechniqueView.setText(secondaryTechnique);
        cb0.setChecked(course.getDidComplete(0));
        cb1.setChecked(course.getDidComplete(1));
        cb2.setChecked(course.getDidComplete(2));

    }
}
