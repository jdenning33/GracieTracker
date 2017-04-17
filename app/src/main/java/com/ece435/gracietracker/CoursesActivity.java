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

//        String[] columns = new String[] { "_id", "primarySkill", "secondarySkill" };
//
//        MatrixCursor matrixCursor= new MatrixCursor(columns);
//        matrixCursor.addRow(new Object[] { 1 , "Trap and Roll Escape – Mount (GU 1)*"       ,"Leg Hook Takedown (GU 6)"                    });
//        matrixCursor.addRow(new Object[] { 2 , "Americana Armlock – Mount (GU 2)"           ,"Clinch (Aggressive Opponent) (GU 7)"         });
//        matrixCursor.addRow(new Object[] { 3 , "Positional Control – Mount (GU 3)"          ,"Body Fold Takedown (GU 14)"                  });
//        matrixCursor.addRow(new Object[] { 4 , "Take the Back + R.N.C. – Mount (GU 4 + 5)"  ,"Clinch (Conservative Opponent) (GU 15)"      });
//        matrixCursor.addRow(new Object[] { 5 , "Punch Block Series (1-4) – Guard (GU 8)"    ,"Guillotine Choke (Standing) (GU 23)"         });
//        matrixCursor.addRow(new Object[] { 6 , "Straight Armlock – Mount (GU 9)"            ,"Guillotine Defense (GU 32)"                  });
//        matrixCursor.addRow(new Object[] { 7 , "Triangle Choke – Guard (GU 10)"             ,"Haymaker Punch Defense (GU 30)"              });
//        matrixCursor.addRow(new Object[] { 8 , "Elevator Sweep – Guard (GU 11)"             ,"Rear Takedown (GU 29) "                      });
//        matrixCursor.addRow(new Object[] { 9 , "Elbow Escape – Mount (GU 12)"               ,"Pull Guard (GU 21)"                          });
//        matrixCursor.addRow(new Object[] { 10, "Positional Control – Side Mount (GU 13)"    ,"Double Leg Takedown (Aggressive) (GU 17)"    });
//        matrixCursor.addRow(new Object[] { 11, "Headlock Counters – Mount (GU 16)"          ,"Standing Headlock Defense (GU 26)"           });
//        matrixCursor.addRow(new Object[] { 12, "Headlock Escape 1 – Side Mount (GU 18)"     ,"Standing Armlock (GU 34)"                    });
//        matrixCursor.addRow(new Object[] { 13, "Straight Armlock – Guard (GU 19)"           ,"Clinch (Aggressive Opponent) (GU 7)"         });
//        matrixCursor.addRow(new Object[] { 14, "Double Ankle Sweep – Guard (GU 20)"         ,"Guillotine Choke (Guard Pull) (GU 23)"       });
//        matrixCursor.addRow(new Object[] { 15, "Headlock Escape 2 – Side Mount (GU 22)"     ,"Clinch (Conservative Opponent) (GU 15)"      });
//        matrixCursor.addRow(new Object[] { 16, "Shrimp Escape – Side Mount (GU 24)"         ,"Body Fold Takedown (GU 14)"                  });
//        matrixCursor.addRow(new Object[] { 17, "Kimura Armlock – Guard (GU 25)"             ,"Leg Hook Takedown (GU 6)"                    });
//        matrixCursor.addRow(new Object[] { 18, "Punch Block Series (5) – Guard (GU 27)"     ,"Haymaker Punch Defense (GU 30)"              });
//        matrixCursor.addRow(new Object[] { 19, "Hook Sweep – Guard (GU 28)"                 ,"Guillotine Defense (GU 32)"                  });
//        matrixCursor.addRow(new Object[] { 20, "Take the Back – Guard (GU 31)"              ,"Standing Headlock Defense (GU 26)"           });
//        matrixCursor.addRow(new Object[] { 21, "Elbow Escape – Side Mount (GU 33)"          ,"Pull Guard (GU 21)"                          });
//        matrixCursor.addRow(new Object[] { 22, "Twisting Arm Control – Mount (GU 35)"       ,"Rear Takedown (GU 29)"                       });
//        matrixCursor.addRow(new Object[] { 23, "Double Underhook Pass – Guard (GU 36)"      ,"Double Leg Takedown (Conservative) (GU 17)"  });

        int[] to = new int[] {R.id.courseNumber, R.id.coursePrimarySkill, R.id.courseSecondarySkill};
        String[] from = Course.courseCursor.getColumnNames();
        SimpleCursorAdapter cursorAdapter =
                new SimpleCursorAdapter(this, R.layout.course_list_item, Course.courseCursor, from, to, 0);

        ListView listView = (ListView) findViewById(R.id.CourseList);
        listView.setAdapter(cursorAdapter);

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
