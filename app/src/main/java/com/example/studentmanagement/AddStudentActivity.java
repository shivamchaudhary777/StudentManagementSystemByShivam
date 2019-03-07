package com.example.studentmanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddStudentActivity extends AppCompatActivity {

    EditText etStudentName;
    EditText etRollNumber;
    Button btnSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);


        etStudentName =findViewById(R.id.enterstudentname);
        etRollNumber =findViewById(R.id.enterrollnumber);
        btnSave =findViewById(R.id.save);


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String getStudentName = etStudentName.getText().toString();
                String getRollNumber = etRollNumber.getText().toString();



                  // validation on name and roll number entered in edit text

                if(getStudentName.length()==0)

                {
                    etStudentName.requestFocus();
                    etStudentName.setError("FIELD CANNOT BE EMPTY");
                }

                else if(!getStudentName.matches("[a-zA-Z ]+"))
                {
                    etStudentName.requestFocus();
                    etStudentName.setError("ENTER ONLY ALPHABETICAL CHARACTER");
                }

                else if(getRollNumber.length()==0)
                {
                    etRollNumber.requestFocus();
                    etRollNumber.setError("FIELD CANNOT BE EMPTY");
                }
                else if(getRollNumber.matches("[a-zA-Z ]+")){
                    etRollNumber.requestFocus();
                    etRollNumber.setError("ENTER ONLY INTEGER VALUE");

                }
                else
                {
                    Intent addResultIntent= new Intent();
                    addResultIntent.putExtra("etStudentName",getStudentName);
                    addResultIntent.putExtra("etRollNumber",getRollNumber);


                    setResult(RESULT_OK,addResultIntent);
                    finish();
                }







            }
        });





    }
}
