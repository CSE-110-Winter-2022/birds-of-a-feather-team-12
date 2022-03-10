package com.example.team12bof;

import com.example.team12bof.db.AppDatabase;
import com.example.team12bof.db.Course;
import com.example.team12bof.db.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortSmall implements Sorter{
    @Override
    public List<Student> sort(AppDatabase db, List<Course> userCourses, String qtr, String year) {
        List<Course> currStudentCourses;
        List<double[]> pairs = new ArrayList<>();
        double score =0;
        List<Student> prioSmall = new ArrayList<>();
        List<Student> students = db.studentDao().getAll();

        for(int i = 0; i< students.size(); i++){
            score =0;
            Student currStudent = students.get(i);
            currStudentCourses = db.coursesDao().getForStudent(currStudent.getStudentId());
            for(int j = 0; j< currStudentCourses.size();j++){
                for(int k =0; k< userCourses.size();k++){
                    if(currStudentCourses.get(j).getText().equals(userCourses.get(k).getText())){
                        score += findScore(currStudentCourses.get(j).getClassSize());
                        }
                    }
                }

            pairs.add(new double[]{score, (double) currStudent.getStudentId()});


        }
        pairs = sortPairs(pairs);
        for(int i=0; i<pairs.size();i++){
            prioSmall.add(db.studentDao().get((int)pairs.get(i)[1]));
        }




        return prioSmall;
    }
    public double findScore(String classSize){

        if(classSize.equals("Tiny (less than 40))")) {
            return 1.0;
        }
        else if (classSize.equals("Small(40-75)")){
            return 0.33;
        }
        else if (classSize.equals("Medium(75-150)")){
            return 0.18;
        }
        else if (classSize.equals("Large(150-250)")){
            return 0.10;
        }
        else if (classSize.equals("Huge(250-400)")){
            return 0.06;
        }
        else {
            return 0.03;
        }

    }

    public List<double[]> sortPairs(List<double[]> scores) {
        Comparator<double[]> sortByScore= (d1,d2) -> Double.compare(d2[0],d1[0]);
        Collections.sort(scores,sortByScore);
        return scores;
    }

}
