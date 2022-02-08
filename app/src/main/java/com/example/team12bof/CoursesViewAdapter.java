package com.example.team12bof;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.team12bof.db.Course;

import java.util.List;

public class CoursesViewAdapter extends RecyclerView.Adapter<CoursesViewAdapter.ViewHolder> {
    private final List<Course> courses;

    public CoursesViewAdapter(List<Course> courses) {
        super();
        this.courses = courses;
    }

    @NonNull
    @Override
    public CoursesViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.courses_row, parent, false);
        // NOTE: courses_row has not been implemented yet

        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return this.courses.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView courseTextView;
        private Course course;

        ViewHolder(View itemView) {
            super(itemView);
            this.courseTextView = itemView.findViewById(R.id.courses_row_text);
            // courses_row_text not implemented yet
        }

        public void setCourse(Course course) {
            this.course = course;

            // text has not been created in the Course class
            // consider text to be: subject + " " + courseNumber + " " + quarter + " " + year
            // ex: "CSE 110 Winter 2022"
            this.courseTextView.setText(course.text);
        }
    }
}
