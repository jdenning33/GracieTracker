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
    private int[] primarySkills;    //Bolded technique on the course description
    private int[] secondarySkills;  //Second technique on the description

    private Date[] courseOfferings; //array of when the course is planned to be taught


    public Course(int number, int[] primarySkills, int[] secondarySkills){
        this.number = number;
        this.primarySkills = primarySkills;
        this.secondarySkills = secondarySkills;
    }

    public int getNumber(){ return number; }
    public String getPrimaryTechnique(){
        return Skill.skillArray.get(primarySkills[0]-1).getName();
    }
    public ArrayList<Skill> getAllTechniques(){
        ArrayList<Skill> skills = new ArrayList<Skill>();

        for(int i=0; i<primarySkills.length; i++){
            skills.add(Skill.skillArray.get(primarySkills[i]-1));
        }
        for(int i=0; i<secondarySkills.length; i++){
            skills.add(Skill.skillArray.get(secondarySkills[i]-1));
        }

        return skills;
    }
    public String getSecondaryTechnique(){
        return Skill.skillArray.get(secondarySkills[0]-1).getName();
    }
    public String getPrimaryTechniqueLink(){
        return Skill.skillArray.get(primarySkills[0]-1).getLink();
    }
    public String getSecondaryTechniqueLink(){
        return Skill.skillArray.get(secondarySkills[0]-1).getLink();
    }


    //List of all Gracie Course offerings
    public static final ArrayList<Course> courseArray = populateCourseArray();

    private static ArrayList<Course> populateCourseArray(){
        String[] columns = new String[] { "_id", "primarySkill", "secondarySkill" };
        ArrayList<Course> courseArray = new ArrayList<Course> (23);

        courseArray.add(new Course( 1 , new int[] {  1}, new int[] { 6}));
        courseArray.add(new Course( 2 , new int[] {  2}, new int[] { 7}));
        courseArray.add(new Course( 3 , new int[] {  3}, new int[] {14}));
        courseArray.add(new Course( 4 , new int[] {4,5}, new int[] {15}));
        courseArray.add(new Course( 5 , new int[] {  8}, new int[] {23}));
        courseArray.add(new Course( 6 , new int[] {  9}, new int[] {32}));
        courseArray.add(new Course( 7 , new int[] { 10}, new int[] {30}));
        courseArray.add(new Course( 8 , new int[] { 11}, new int[] {29}));
        courseArray.add(new Course( 9 , new int[] { 12}, new int[] {21}));
        courseArray.add(new Course( 10, new int[] { 13}, new int[] {17}));
        courseArray.add(new Course( 11, new int[] { 16}, new int[] {26}));
        courseArray.add(new Course( 12, new int[] { 18}, new int[] {34}));
        courseArray.add(new Course( 13, new int[] { 19}, new int[] { 7}));
        courseArray.add(new Course( 14, new int[] { 20}, new int[] {23}));
        courseArray.add(new Course( 15, new int[] { 22}, new int[] {15}));
        courseArray.add(new Course( 16, new int[] { 24}, new int[] {14}));
        courseArray.add(new Course( 17, new int[] { 25}, new int[] { 6}));
        courseArray.add(new Course( 18, new int[] { 27}, new int[] {30}));
        courseArray.add(new Course( 19, new int[] { 28}, new int[] {32}));
        courseArray.add(new Course( 20, new int[] { 31}, new int[] {26}));
        courseArray.add(new Course( 21, new int[] { 33}, new int[] {21}));
        courseArray.add(new Course( 22, new int[] { 35}, new int[] {29}));
        courseArray.add(new Course( 23, new int[] { 36}, new int[] {17}));


        return courseArray;
    }
}

