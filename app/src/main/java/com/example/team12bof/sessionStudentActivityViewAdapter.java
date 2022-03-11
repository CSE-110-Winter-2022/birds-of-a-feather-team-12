package com.example.team12bof;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.team12bof.db.Course;
import com.example.team12bof.db.Student;

import java.util.List;

public class sessionStudentActivityViewAdapter extends RecyclerView.Adapter<sessionStudentActivityViewAdapter.ViewHolder> {
    private final List<? extends Student> myclassmates;


    public sessionStudentActivityViewAdapter(List<? extends Student> myclassmates) {
        super();
        this.myclassmates = myclassmates;
    }

    @NonNull
    @Override
    public sessionStudentActivityViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.sessionstudent_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull sessionStudentActivityViewAdapter.ViewHolder holder, int position) {
        holder.setClassmate(myclassmates.get(position));
    }

    @Override
    public int getItemCount() {
        return this.myclassmates.size();
    }

    public static class ViewHolder
            extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        private final TextView myclassmateNameView;
        private Student myclassmate;

        ViewHolder(View itemView) {
            super(itemView);
            this.myclassmateNameView = itemView.findViewById(R.id.ses_row_name);
            itemView.setOnClickListener(this);
        }

        public void setClassmate(Student classmate) {
            this.myclassmate = classmate;
            this.myclassmateNameView.setText(classmate.getName());
        }

        @Override
        public void onClick(View view) {
            Context context = view.getContext();
            Intent intent = new Intent(context, sessionCourseActivity.class);
            intent.putExtra("classmate_id", this.myclassmate.getStudentId());

            context.startActivity(intent);
        }
    }

}
