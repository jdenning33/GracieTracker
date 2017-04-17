package com.ece435.gracietracker;

import android.database.MatrixCursor;

import java.util.Date;

/**
 * Created by jdenn on 4/17/2017.
 */

public class Course {
    private int number;             //Course number (1 - 23)
    private String primarySkill;    //Bolded technique on the course description
    private String secondarySkill;  //Second technique on the description
    private int[] skillNumbers;     //The corresponding skills that the course teaches (GU1 - GU36)

    private int timesCompleted;     //number of times the course has been completed

    private Date[] courseOfferings; //array of when the course is planned to be taught


    //Defines the generic properties of all gracie courses i.e. num, skill1, skill2
    //User info should be placed into a separate Cursor that can be modified during runtime
    public static final MatrixCursor courseCursor = populateCourseCursor();


    public Course(int number){

    }

    private static MatrixCursor populateCourseCursor(){
        String[] columns = new String[] { "_id", "primarySkill", "secondarySkill" };
        MatrixCursor courseCursor= new MatrixCursor(columns);

        courseCursor.addRow(new Object[] { 1 , "Trap and Roll Escape – Mount (GU 1)*"       ,"Leg Hook Takedown (GU 6)"                    });
        courseCursor.addRow(new Object[] { 2 , "Americana Armlock – Mount (GU 2)"           ,"Clinch (Aggressive Opponent) (GU 7)"         });
        courseCursor.addRow(new Object[] { 3 , "Positional Control – Mount (GU 3)"          ,"Body Fold Takedown (GU 14)"                  });
        courseCursor.addRow(new Object[] { 4 , "Take the Back + R.N.C. – Mount (GU 4 + 5)"  ,"Clinch (Conservative Opponent) (GU 15)"      });
        courseCursor.addRow(new Object[] { 5 , "Punch Block Series (1-4) – Guard (GU 8)"    ,"Guillotine Choke (Standing) (GU 23)"         });
        courseCursor.addRow(new Object[] { 6 , "Straight Armlock – Mount (GU 9)"            ,"Guillotine Defense (GU 32)"                  });
        courseCursor.addRow(new Object[] { 7 , "Triangle Choke – Guard (GU 10)"             ,"Haymaker Punch Defense (GU 30)"              });
        courseCursor.addRow(new Object[] { 8 , "Elevator Sweep – Guard (GU 11)"             ,"Rear Takedown (GU 29) "                      });
        courseCursor.addRow(new Object[] { 9 , "Elbow Escape – Mount (GU 12)"               ,"Pull Guard (GU 21)"                          });
        courseCursor.addRow(new Object[] { 10, "Positional Control – Side Mount (GU 13)"    ,"Double Leg Takedown (Aggressive) (GU 17)"    });
        courseCursor.addRow(new Object[] { 11, "Headlock Counters – Mount (GU 16)"          ,"Standing Headlock Defense (GU 26)"           });
        courseCursor.addRow(new Object[] { 12, "Headlock Escape 1 – Side Mount (GU 18)"     ,"Standing Armlock (GU 34)"                    });
        courseCursor.addRow(new Object[] { 13, "Straight Armlock – Guard (GU 19)"           ,"Clinch (Aggressive Opponent) (GU 7)"         });
        courseCursor.addRow(new Object[] { 14, "Double Ankle Sweep – Guard (GU 20)"         ,"Guillotine Choke (Guard Pull) (GU 23)"       });
        courseCursor.addRow(new Object[] { 15, "Headlock Escape 2 – Side Mount (GU 22)"     ,"Clinch (Conservative Opponent) (GU 15)"      });
        courseCursor.addRow(new Object[] { 16, "Shrimp Escape – Side Mount (GU 24)"         ,"Body Fold Takedown (GU 14)"                  });
        courseCursor.addRow(new Object[] { 17, "Kimura Armlock – Guard (GU 25)"             ,"Leg Hook Takedown (GU 6)"                    });
        courseCursor.addRow(new Object[] { 18, "Punch Block Series (5) – Guard (GU 27)"     ,"Haymaker Punch Defense (GU 30)"              });
        courseCursor.addRow(new Object[] { 19, "Hook Sweep – Guard (GU 28)"                 ,"Guillotine Defense (GU 32)"                  });
        courseCursor.addRow(new Object[] { 20, "Take the Back – Guard (GU 31)"              ,"Standing Headlock Defense (GU 26)"           });
        courseCursor.addRow(new Object[] { 21, "Elbow Escape – Side Mount (GU 33)"          ,"Pull Guard (GU 21)"                          });
        courseCursor.addRow(new Object[] { 22, "Twisting Arm Control – Mount (GU 35)"       ,"Rear Takedown (GU 29)"                       });
        courseCursor.addRow(new Object[] { 23, "Double Underhook Pass – Guard (GU 36)"      ,"Double Leg Takedown (Conservative) (GU 17)"  });

        return courseCursor;
    }
}
