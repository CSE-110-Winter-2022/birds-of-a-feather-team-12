package com.example.team12bof;

import com.example.team12bof.db.AppDatabase;
import com.example.team12bof.db.Course;
import com.example.team12bof.db.Student;

import java.util.ArrayList;
import java.util.List;

public class SortQuarterOnly implements Sorter{

    @Override
    public List<Student> sort(AppDatabase db, List<Course> userCourses, String qtr, String year) {
        List<Course> currStudentCourses;
        List<Student>  thisQuarter = new ArrayList<>();
        List<Student> students = db.studentDao().getAll();

        first: for(int i =0; i< students.size();i++){
            Student currStudent = students.get(i);
            currStudentCourses = db.coursesDao().getForStudent(currStudent.getStudentId());
            second: for(int j = 0; j< currStudentCourses.size();j++){
                third: for(int k =0; k< userCourses.size();k++){
                    if(currStudentCourses.get(j).getText().equals(userCourses.get(k).getText())
                            && currStudentCourses.get(j).getQuarter().equals(qtr)
                            && currStudentCourses.get(j).getYear().equals(year)){

                        thisQuarter.add(currStudent);
                        break second;
                    }
                }
            }


        }
        return thisQuarter;


    }
}
