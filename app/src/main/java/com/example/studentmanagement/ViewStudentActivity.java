package com.example.studentmanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ViewStudentActivity extends AppCompatActivity {
    TextView tvName;
    TextView tvRollNumber;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_student);



        tvName =findViewById(R.id.viewtextview1);
        tvRollNumber =findViewById(R.id.viewtextview2);


        Intent viewIntent = getIntent();

        String sname = viewIntent.getStringExtra("viewName");
        int sRollNumber =viewIntent.getIntExtra("viewRollNumber",0);





         tvName.setText(sname);
         tvRollNumber.setText(Integer.toString(sRollNumber));

    }
}
