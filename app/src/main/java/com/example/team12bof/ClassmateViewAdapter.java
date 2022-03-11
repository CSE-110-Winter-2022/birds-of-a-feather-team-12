package com.example.team12bof;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.team12bof.db.Student;

import java.util.List;

public class ClassmateViewAdapter extends RecyclerView.Adapter<ClassmateViewAdapter.ViewHolder> {
    private final List<? extends Student> classmates;

    public static StringBuilder myIds = new StringBuilder();
    public ClassmateViewAdapter(List<? extends Student> classmates) {
        super();
        this.classmates = classmates;
    }

    @NonNull
    @Override
    public ClassmateViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.classmate_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassmateViewAdapter.ViewHolder holder, int position) {
        holder.setClassmate(classmates.get(position));
    }

    @Override
    public int getItemCount() {
        return this.classmates.size();
    }

    public static class ViewHolder
            extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        private final TextView classmateNameView;
        private final ImageView URL;
        private Student classmate;

        ViewHolder(View itemView) {
            super(itemView);
            this.classmateNameView = itemView.findViewById(R.id.classmate_row_name);
            this.URL = itemView.findViewById(R.id.URL);
            itemView.setOnClickListener(this);
        }

        public void setClassmate(Student classmate) {
            this.classmate = classmate;
            this.classmateNameView.setText(classmate.getName());

            Glide.with(itemView).load(this.classmate.getURL()).into(URL);
        }

        @Override
        public void onClick(View view) {
            Context context = view.getContext();
            Intent intent = new Intent(context, ClassmateDetailActivity.class);
            intent.putExtra("classmate_id", this.classmate.getStudentId());

            context.startActivity(intent);
        }
    }

}
