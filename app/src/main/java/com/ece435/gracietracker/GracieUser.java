package com.ece435.gracietracker;

import android.util.Log;

import java.io.Console;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

/**
 * Created by jdenn on 4/28/2017.
 */

public class GracieUser {
    public String preferredName;
    public String email;
    public String uid;

//    public Date dob;
    public int currentBelt; //1 = White
//    public boolean[][][] courseCompletions; // [Belt][Course][Class]
//    public boolean[][] reflexCourseCompletions; // [Belt][Class]

    public String courseCompletionsString;
    public String reflexCourseCompletionsString;

    private Boolean[][][] courseCompletions;
    private Boolean[][] reflexCourseCompletions;
    private static final char NEXT_ITEM = ',';

    public GracieUser() {
        Log.e("FuckMe", "new gracie user ");

//        currentBelt = isYouth(dob) ? 1 : 5;
        Random random = new Random();

        //Initialize Course Completions -- 7 belts, 23 courses, 3 classes each
        courseCompletions = new Boolean[7][23][3];
        for(Boolean[][] bool2 : courseCompletions){
            for(Boolean[] bool1 : bool2){
                for(int i=0; i<bool1.length; i++){
                    bool1[i] = random.nextBoolean();
                }
            }
        }
        courseCompletionsString = serialize3d(courseCompletions);

        //Initialize relfexCourseCompletions -- 7 belts, 12 RD courses
        reflexCourseCompletions = new Boolean[7][12];
        for(Boolean[] bool1 : reflexCourseCompletions){
            for(int i=0; i<bool1.length; i++){
                bool1[i] = random.nextBoolean();
            }
        }
        reflexCourseCompletionsString = serialize2d(reflexCourseCompletions);
        Log.e("FuckMe", ""+ reflexCourseCompletionsString);
        reflexCourseCompletions = deserialize2d(reflexCourseCompletionsString);
        reflexCourseCompletionsString = serialize2d(reflexCourseCompletions);
        Log.e("FuckMe", ""+ reflexCourseCompletionsString);
    }

    public boolean isYouth(Date birthday){
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.YEAR, -18);
        Date dob18 = cal.getTime();


        if(birthday.before(dob18)) return true;
        else return false;
    }

    public String serialize3d(Boolean[][][] bool3d){
        String string = "";

        string += "[";
        for(Boolean[][] bool2d : bool3d){
            string += serialize2d(bool2d);
        }
        string += "]";

        return string;
    }
    public String serialize2d(Boolean[][] bool2d){
        String string = "";

        string += "[";
        for(Boolean[] bool1d : bool2d){
            string += "[";
            for(Boolean value : bool1d){
                if(value == null) continue;
                if(value) string += "1";
                else string += "0";
            }
            string += "]";
        }
        string += "]";
        return string;
    }

    public Boolean[][][] deserialize3d(String input){
        int depth = 0; // if it sees [ ++ if it sees ] --
        int length = 0; // if it sees ] at the correct depth ++

        ArrayList<String> subArrays = new ArrayList<String>();
        String subArray = "";

        int index = 0; //where are we in the string
        char item;

        while( !(index!=0 && depth==0) && index < input.length()){

            item = input.charAt(index);

            if(item == '[') depth++;

            if(depth > 1) {
                subArray += item;
            }

            if(item == ']') depth--;

            if(item == '[' && depth == 2) length++;
            if(item == ']' && depth == 1) {
                subArrays.add(subArray);
                subArray = "";
            }

            index++;
        }

        Boolean[][][] subArraysF = new Boolean[subArrays.size()][][];
        int i=0;
        for(String subArrayT : subArrays){
            subArraysF[i] = deserialize2d(subArrayT);
            i++;
        }

        return subArraysF;
    }

    public Boolean[][] deserialize2d(String input){
        int depth = 0; // if it sees [ ++ if it sees ] --
        int length = 0; // if it sees ] at the correct depth ++

        ArrayList< ArrayList<Boolean>> subArrays = new ArrayList<ArrayList<Boolean>>();
        ArrayList<Boolean> subArray = new ArrayList<Boolean>();

        int index = 0; //where are we in the string
        char item;

        while( !(index!=0 && depth==0) && index < input.length()){

            item = input.charAt(index);

            if(item == '[') depth++;
            if(item == ']') depth--;

            if(item == '[' && depth == 1) length++;
            if(item == ']' && depth == 1) {
                subArrays.add(subArray);
                subArray = new ArrayList<Boolean>();
            }

            if(item != '[' && item != ']') {
                subArray.add( item == '0' ? false : true );
            }
            index++;
        }

        Boolean[][] subArraysF = new Boolean[subArrays.size()][];
        int i=0;
        for(ArrayList<Boolean> subArrayT : subArrays){
            subArraysF[i] = (Boolean[]) subArrayT.toArray( new Boolean[subArrayT.size()] );
            i++;
        }

        return subArraysF;
    }
}
