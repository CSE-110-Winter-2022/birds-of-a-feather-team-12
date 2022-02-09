package com.example.team12bof;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ClassmateViewAdapter extends RecyclerView.Adapter<ClassmateViewAdapter.ViewHolder> {
    private final List<? extends IStudent> classmates;

    public ClassmateViewAdapter(List<? extends IStudent> classmates) {
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
        private IStudent classmate;

        ViewHolder(View itemView) {
            super(itemView);
            this.classmateNameView = itemView.findViewById(R.id.classmate_row_name);
            itemView.setOnClickListener(this);
        }

        public void setClassmate(IStudent classmate) {
            this.classmate = classmate;
            this.classmateNameView.setText(classmate.getName());
        }

        @Override
        public void onClick(View view) {
            Context context = view.getContext();
            Intent intent = new Intent(context, ClassmateDetailActivity.class);
            intent.putExtra("classmate_name", this.classmate.getName());
            //intent.putExtra("classmate_courses", this.classmate.getCourses().toArray(new String[0]));
            context.startActivity(intent);
        }
    }

}
