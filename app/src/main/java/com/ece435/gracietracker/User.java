package com.ece435.gracietracker;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by jdenn on 4/28/2017.
 */

public class User {
    public Date birthday;
    public int currentBelt; //1 = White
    public boolean[][][] courseCompletions; // [Belt][Course][Class]
    public boolean[][] reflexCourseCompletions; // [Belt][Class]


    public User () {
        currentBelt = isYouth(birthday) ? 1 : 5;
        courseCompletions = new boolean[7][23][3];
        reflexCourseCompletions = new boolean[7][12];
    }

    public boolean isYouth(Date birthday){
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.YEAR, -18);
        Date dob18 = cal.getTime();


        if(birthday.before(dob18)) return true;
        else return false;
    }
}
