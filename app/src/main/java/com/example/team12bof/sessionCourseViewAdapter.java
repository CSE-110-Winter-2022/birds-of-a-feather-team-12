package com.example.team12bof;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.team12bof.db.Course;

import java.util.List;

public class sessionCourseViewAdapter extends RecyclerView.Adapter<sessionCourseViewAdapter.ViewHolder> {
    private final List<Course> mycourses;

    public sessionCourseViewAdapter(List<Course> courses) {
        super();
        this.mycourses = courses;
    }

    @NonNull
    @Override
    public sessionCourseViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.coursesession_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setCourse(mycourses.get(position));
    }

    @Override
    public int getItemCount() {
        return this.mycourses.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView mycourseTextView;
        private Course mycourse;

        ViewHolder(View itemView) {
            super(itemView);
            this.mycourseTextView = itemView.findViewById(R.id.coursesSession_row_text);
        }

        public void setCourse(Course course) {
            this.mycourse = course;

            this.mycourseTextView.setText(course.text);
        }
    }


}

