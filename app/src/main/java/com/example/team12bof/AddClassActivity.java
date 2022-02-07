package com.example.team12bof;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.team12bof.db.AppDatabase;
import com.example.team12bof.db.Course;
import com.example.team12bof.db.StudentWithCourses;

import org.w3c.dom.Text;

public class AddClassActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private int num_courses;
    private DummyStudent user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class);
        loadProfile();
        setTitle(R.string.add_class_title);

        user = new DummyStudent(0,"user");
        num_courses =0;

        Spinner school_year = findViewById(R.id.school_year);
        ArrayAdapter<CharSequence> adapter_year = ArrayAdapter.createFromResource(this, R.array.years, android.R.layout.simple_spinner_item);
        adapter_year.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        school_year.setAdapter(adapter_year);
        school_year.setOnItemSelectedListener(this);

        Spinner quarter = findViewById(R.id.quarter);
        ArrayAdapter<CharSequence> adapter_quarter = ArrayAdapter.createFromResource(this, R.array.quarters, android.R.layout.simple_spinner_item);
        adapter_quarter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        quarter.setAdapter(adapter_quarter);
        quarter.setOnItemSelectedListener(this);

        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        int year = preferences.getInt("Year", 0);
        school_year.setSelection(year);
        int quarterrr = preferences.getInt("Quarter", 0);
        quarter.setSelection(quarterrr);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        saveProfile();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(adapterView.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void loadProfile(){

        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        String courseNum = preferences.getString("Course Number","");
        String courseSub = preferences.getString("Course Subject", "");
        TextView nnn = findViewById(R.id.number);
        TextView sss = findViewById(R.id.subject);
        nnn.setText(courseNum);
        sss.setText(courseSub);

    }

    public void saveProfile(){
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        TextView nnn = findViewById(R.id.number);
        editor.putString("Course Number", nnn.getText().toString());

        TextView sss = findViewById(R.id.subject);
        editor.putString("Course Subject", sss.getText().toString());

        Spinner school_year = findViewById(R.id.school_year);
        Spinner quarter = findViewById(R.id.quarter);
        editor.putInt("Year", school_year.getSelectedItemPosition());
        editor.putInt("Quarter", quarter.getSelectedItemPosition());
        editor.apply();
    }

    public void onEnterClicked(View view) {


        TextView subject = findViewById(R.id.subject);
        TextView courseNumber = findViewById(R.id.number);
        Spinner school_year = findViewById(R.id.school_year);
        Spinner quarter = findViewById(R.id.quarter);
        if(subject.getText().toString().equals("") ||courseNumber.getText().toString().equals("")){
            Utilities.showAlert(this, "Invalid Entry");



        }
        else {
            Course course = new Course(num_courses,user.getId(),courseNumber.getText().toString(),subject.getText().toString(),school_year.getSelectedItem().toString(),quarter.getSelectedItem().toString());
            user.addCourse(course);
            num_courses++;

        }
    }

    public boolean hasNum() {
        TextView numView = this.findViewById(R.id.number);

        return !TextUtils.isEmpty(numView.getText().toString());
    }

    public boolean hasSubject() {
        TextView subjectView = this.findViewById(R.id.subject);

        return !TextUtils.isEmpty(subjectView.getText());
    }
}