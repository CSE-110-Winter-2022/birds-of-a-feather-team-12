package com.example.team12bof;

import com.example.team12bof.db.AppDatabase;
import com.example.team12bof.db.Course;
import com.example.team12bof.db.Student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class SortRecent implements Sorter {
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
                        score += findScore(currStudentCourses.get(j).getQuarter(),currStudentCourses.get(j).getYear(),qtr,year);
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
    public double findScore(String studentQtr, String studentYear,String currQtr, String currYear){
        int numStudentYear=Integer.parseInt(studentYear);
        int numCurrYear=Integer.parseInt(currYear);
        int index = 0;
        if(studentQtr == "Summer Session I" || studentQtr == "Summer Session II" ||studentQtr == "Special Summer Session" ){
            studentQtr="Summer";
        }
        if(currQtr == "Summer Session I" || currQtr == "Summer Session II" ||currQtr == "Special Summer Session" ){
            currQtr="Summer";
            index = 1;
        }
        if(currQtr == "Fall" ){
            index = 0;
        }
        if(currQtr == "Spring" ){
            index = 2;
        }
        if(currQtr == "Winter" ){
            index = 3;
        }


        if(numStudentYear == numCurrYear && studentQtr.equals(currQtr)) {
            return 5;
        }
        if(numStudentYear <= numCurrYear-2) {
            return 1;
        }

        List<String> l = new ArrayList<String>();
        l.add("Fall");
        l.add("Summer");
        l.add("Spring");
        l.add("Winter");
        int currIdx = 0;
        int score = 5;
        String prevQtr = "";
        for (int i=index;i<l.size()+index+2;i++) {
            currIdx = l.size()%i;
            if(i > index + 2){
                score -= 1;
            }
            if(prevQtr=="Winter"){
                numCurrYear -= 1;
            }
            if(studentQtr.equals(l.get(currIdx))&&numCurrYear == numStudentYear){
                return score;
            }
            prevQtr = l.get(currIdx);


        }
        return score;



    }

    public List<double[]> sortPairs(List<double[]> scores) {
        Comparator<double[]> sortByScore= (d1, d2) -> Double.compare(d2[0],d1[0]);
        Collections.sort(scores,sortByScore);
        return scores;
    }

}

