package com.ece435.gracietracker;

import android.content.Context;
import android.database.MatrixCursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jdenn on 4/17/2017.
 */

public class Course {
    private int number;             //Course number (1 - 23)
    private String primarySkill;    //Bolded technique on the course description
    private String secondarySkill;  //Second technique on the description
    private int[] skillNumbers;     //The corresponding skills that the course teaches (GU1 - GU36)

    //booleans for how many times the course has been completed on this belt
    private boolean[] didComplete = new boolean[] {false, false, false};


    private Date[] courseOfferings; //array of when the course is planned to be taught


    //List of all Gracie Course offerings
    public static final ArrayList<Course> courseArray = populateCourseArray();


    public Course(int number, String primarySkill, String secondarySkill){
        this.number = number;
        this.primarySkill = primarySkill;
        this.secondarySkill = secondarySkill;
    }

    public void toggleComplete(int number){
        didComplete[number] = !didComplete[number];
    }

    public int getNumber(){
        return number;
    }
    public String getPrimaryTechnique(){
        return primarySkill;
    }
    public String getSecondaryTechnique(){
        return secondarySkill;
    }
    public boolean getDidComplete(int number){
        return didComplete[number];
    }

    private static ArrayList<Course> populateCourseArray(){
        String[] columns = new String[] { "_id", "primarySkill", "secondarySkill" };
        ArrayList<Course> courseArray = new ArrayList<Course> (23);

        courseArray.add(new Course( 1 , "Trap and Roll Escape – Mount (GU 1)*"       ,"Leg Hook Takedown (GU 6)"                    ));
        courseArray.add(new Course( 2 , "Americana Armlock – Mount (GU 2)"           ,"Clinch (Aggressive Opponent) (GU 7)"         ));
        courseArray.add(new Course( 3 , "Positional Control – Mount (GU 3)"          ,"Body Fold Takedown (GU 14)"                  ));
        courseArray.add(new Course( 4 , "Take the Back + R.N.C. – Mount (GU 4 + 5)"  ,"Clinch (Conservative Opponent) (GU 15)"      ));
        courseArray.add(new Course( 5 , "Punch Block Series (1-4) – Guard (GU 8)"    ,"Guillotine Choke (Standing) (GU 23)"         ));
        courseArray.add(new Course( 6 , "Straight Armlock – Mount (GU 9)"            ,"Guillotine Defense (GU 32)"                  ));
        courseArray.add(new Course( 7 , "Triangle Choke – Guard (GU 10)"             ,"Haymaker Punch Defense (GU 30)"              ));
        courseArray.add(new Course( 8 , "Elevator Sweep – Guard (GU 11)"             ,"Rear Takedown (GU 29) "                      ));
        courseArray.add(new Course( 9 , "Elbow Escape – Mount (GU 12)"               ,"Pull Guard (GU 21)"                          ));
        courseArray.add(new Course( 10, "Positional Control – Side Mount (GU 13)"    ,"Double Leg Takedown (Aggressive) (GU 17)"    ));
        courseArray.add(new Course( 11, "Headlock Counters – Mount (GU 16)"          ,"Standing Headlock Defense (GU 26)"           ));
        courseArray.add(new Course( 12, "Headlock Escape 1 – Side Mount (GU 18)"     ,"Standing Armlock (GU 34)"                    ));
        courseArray.add(new Course( 13, "Straight Armlock – Guard (GU 19)"           ,"Clinch (Aggressive Opponent) (GU 7)"         ));
        courseArray.add(new Course( 14, "Double Ankle Sweep – Guard (GU 20)"         ,"Guillotine Choke (Guard Pull) (GU 23)"       ));
        courseArray.add(new Course( 15, "Headlock Escape 2 – Side Mount (GU 22)"     ,"Clinch (Conservative Opponent) (GU 15)"      ));
        courseArray.add(new Course( 16, "Shrimp Escape – Side Mount (GU 24)"         ,"Body Fold Takedown (GU 14)"                  ));
        courseArray.add(new Course( 17, "Kimura Armlock – Guard (GU 25)"             ,"Leg Hook Takedown (GU 6)"                    ));
        courseArray.add(new Course( 18, "Punch Block Series (5) – Guard (GU 27)"     ,"Haymaker Punch Defense (GU 30)"              ));
        courseArray.add(new Course( 19, "Hook Sweep – Guard (GU 28)"                 ,"Guillotine Defense (GU 32)"                  ));
        courseArray.add(new Course( 20, "Take the Back – Guard (GU 31)"              ,"Standing Headlock Defense (GU 26)"           ));
        courseArray.add(new Course( 21, "Elbow Escape – Side Mount (GU 33)"          ,"Pull Guard (GU 21)"                          ));
        courseArray.add(new Course( 22, "Twisting Arm Control – Mount (GU 35)"       ,"Rear Takedown (GU 29)"                       ));
        courseArray.add(new Course( 23, "Double Underhook Pass – Guard (GU 36)"      ,"Double Leg Takedown (Conservative) (GU 17)"  ));


        return courseArray;
    }
}
