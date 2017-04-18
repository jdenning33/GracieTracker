package com.ece435.gracietracker;

import android.content.Intent;
import android.database.MatrixCursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class CoursesActivity extends AppCompatActivity {
    public static final String COURSE_ID = "com.ece435.gracietracker.courseID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        CourseListAdapter courseAdapter = new CourseListAdapter(this, R.layout.course_list_item, Course.courseArray);

        ListView listView = (ListView) findViewById(R.id.CourseList);
        listView.setAdapter(courseAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                goToCourseView(position);
            }
        });
    }

    public void goToCourseView(int coursePrimary) {
        Intent intent = new Intent(this, CourseActivity.class);
        intent.putExtra(COURSE_ID, coursePrimary);
        startActivity(intent);
    }

}
