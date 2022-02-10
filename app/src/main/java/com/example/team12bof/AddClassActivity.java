package com.example.team12bof;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import com.example.team12bof.db.Student;


import org.w3c.dom.Text;

/**
 * This class is designed to add new Course based on course ID and course field
 * and also show the previous entry
 */
public class AddClassActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private int num_courses;
    private DummyStudent user;
    private Student me;
    private AppDatabase db;
//
    /**
     *
     * @param savedInstanceState
     */

    /**
     * This method is designed to set the course number, course field
     * the year and the quarter
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class);
        loadProfile();
        setTitle(R.string.add_class_title);
        me = new Student("Me");
        me.setStudentId(3);
        db = AppDatabase.singleton(getApplicationContext());
        db.studentDao().insert(me);
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

    /**
     * This method controls what is inserted to save it
     */
    @Override
    protected void onDestroy(){
        super.onDestroy();
        saveProfile();
    }

    /**
     * This method is designed to show the selected item
     * @param adapterView
     * @param view
     * @param i
     * @param l
     */
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(adapterView.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    /**
     * This method is designed to load the entry that was inserted previously
     */
    public void loadProfile(){

        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        String courseNum = preferences.getString("Course Number","");
        String courseSub = preferences.getString("Course Subject", "");
        TextView nnn = findViewById(R.id.number);
        TextView sss = findViewById(R.id.subject);
        nnn.setText(courseNum);
        sss.setText(courseSub);

    }

    /**
     * This method is designed to save the information of the
     * intended course that we insert
     */
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

    /**
     * This method is control the entry after pushing
     * enter button. If the insert information is valid, it shows them
     * If it's not valid, it sends the invalid message
     * @param view
     */
    public void onEnterClicked(View view) {

        TextView subject = findViewById(R.id.subject);
        TextView courseNumber = findViewById(R.id.number);
        Spinner school_year = findViewById(R.id.school_year);
        Spinner quarter = findViewById(R.id.quarter);
        if(subject.getText().toString().equals("") ||courseNumber.getText().toString().equals("")){
            Utilities.showAlert(this, "Invalid Entry");
        }
        else {
            Course course = new Course(me.getStudentId(),
                    courseNumber.getText().toString(),
                    subject.getText().toString(),
                    school_year.getSelectedItem().toString(),
                    quarter.getSelectedItem().toString());
            num_courses++;
            user.addCourse(course);
            db.coursesDao().insert(course);
            Toast.makeText(AddClassActivity.this,"Course added", Toast.LENGTH_SHORT);
        }
    }

    /**
     * this method return whether the course number we
     * inserted is empty or not
     * @return
     */
    public boolean hasNum() {
        TextView numView = this.findViewById(R.id.number);

        return !TextUtils.isEmpty(numView.getText().toString());
    }

    /**
     * this method return whether the course number we
     * inserted is empty or not
     * @return
     */
    public boolean hasSubject() {
        TextView subjectView = this.findViewById(R.id.subject);

        return !TextUtils.isEmpty(subjectView.getText());
    }

    public void onDoneButtonClicked(View view) {
        saveProfile();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}