package com.ece435.gracietracker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by jdenn on 4/28/2017.
 */

public class GracieUser {
//    private static GracieUser CURRENTUSER;

    public String preferredName = "Change";
    public String email = "Change";
    public String uid = "Change";

//    public Date dob;
    public int currentBelt = 0; //0=needtochange, 1 = White
//    public boolean[][][] courseCompletions; // [Belt][Course][Class]
//    public boolean[][] reflexCourseCompletions; // [Belt][Class]

    public String courseCompletionsString;
    public String reflexCourseCompletionsString;

    private Boolean[][][] courseCompletions;
    private Boolean[][] reflexCourseCompletions;
    private static final char NEXT_ITEM = ',';

    public GracieUser() {
//        currentBelt = isYouth(dob) ? 1 : 5;

        //Initialize Course Completions -- 15 belts, 23 courses, 3 classes each
        courseCompletions = new Boolean[15][23][3];
        for(Boolean[][] bool2 : courseCompletions){
            for(Boolean[] bool1 : bool2){
                for(int i=0; i<bool1.length; i++){
                    bool1[i] = false;
                }
            }
        }
        courseCompletionsString = serialize3d(courseCompletions);

        //Initialize relfexCourseCompletions -- 15 belts, 12 RD courses
        reflexCourseCompletions = new Boolean[15][12];
        for(Boolean[] bool1 : reflexCourseCompletions){
            for(int i=0; i<bool1.length; i++){
                bool1[i] = false;
            }
        }
        reflexCourseCompletionsString = serialize2d(reflexCourseCompletions);
    }

    ////////////////////////////////////////////////////////////////////////////////////
    ///     Skill Course Methods
    ////////////////////////////////////////////////////////////////////////////////////
    public boolean didCompleteCourse(int course, int num){
        return didCompleteCourse(currentBelt, course, num);
    }
    public boolean didCompleteCourse(int belt, int course, int num){
        return courseCompletions[belt][course-1][num];
    }
    public void toggleCompletedCourse(int course, int num){
        toggleCompletedCourse(currentBelt, course, num);
    }
    public void toggleCompletedCourse(int belt, int course, int num){
        courseCompletions[belt][course-1][num] = !courseCompletions[belt][course-1][num];
        courseCompletionsString = serialize3d(courseCompletions);
        Firebase.commitGracieUserToDB();
    }

    ////////////////////////////////////////////////////////////////////////////////////
    ///     Reflex Course Methods
    ////////////////////////////////////////////////////////////////////////////////////
    public boolean didCompleteReflex(int num){
        return didCompleteReflex(currentBelt, num);
    }
    public boolean didCompleteReflex(int belt, int num){
        return reflexCourseCompletions[belt][num];
    }
    public void toggleCompletedReflex(int num){
        toggleCompletedReflex(currentBelt, num);
    }
    public void toggleCompletedReflex(int belt, int num){
        reflexCourseCompletions[belt][num] = !reflexCourseCompletions[belt][num];
        reflexCourseCompletionsString = serialize2d(reflexCourseCompletions);
        Firebase.commitGracieUserToDB();
    }


    public boolean isYouth(Date birthday){
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.YEAR, -18);
        Date dob18 = cal.getTime();


        if(birthday.before(dob18)) return true;
        else return false;
    }

    public void update(GracieUser gracieUser){
        this.preferredName = gracieUser.preferredName;
        this.currentBelt = gracieUser.currentBelt;
        this.courseCompletionsString = gracieUser.courseCompletionsString;
        this.courseCompletions = deserialize3d(courseCompletionsString);
        this.reflexCourseCompletionsString = gracieUser.reflexCourseCompletionsString;
        this.reflexCourseCompletions = deserialize2d(reflexCourseCompletionsString);
    }

    public static String getBeltColor(int coursenum){
        switch (coursenum){
            case 0: return "Change";
            case 1: return "White";
            case 2: return "Grey";
            case 3: return "Yellow-White";
            case 4: return "Yellow";
            case 5: return "Orange-White";
            case 6: return "Orange";
            case 7: return "Green-White";
            case 8: return "Green";
            case 9: return "Blue-White";
            case 10: return "Blue";
            case 11: return "Purple";
            case 12: return "Brown";
            case 13: return "Black";
            case 14: return "Black+";
            default: return "Undefined";
        }
    }

    public int getNumCoursesTilNext(){
        int x = getNumCoursesTaken(currentBelt);
        return 45-x;
    }

    public int getNumCoursesTaken(int belt){
        int numTaken = 0;
        for(Boolean[] bool1d : courseCompletions[belt]){
            for(int i=0; i<3; i++) if(bool1d[i]) numTaken++;
        }
        return numTaken;
    }


    //////////////////////////////////////////////////////////////////////////////////////
    ///     Serializer and de-serializer methods for classProgress arrays
    //////////////////////////////////////////////////////////////////////////////////////
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
