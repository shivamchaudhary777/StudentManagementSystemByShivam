package com.example.studentmanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditStudentActivity extends AppCompatActivity {


    EditText etStudentName;
    EditText etRollNumber;
    Button btnEdit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);


        etStudentName=findViewById(R.id.editstudentname);
        etRollNumber =findViewById(R.id.editrollnumber);
        btnEdit =findViewById(R.id.edit);




        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String getEditedStudentName =etStudentName.getText().toString();
                String getEditedStudentRoll =etRollNumber.getText().toString();


                // validate on name and id that are edited
                if(getEditedStudentName.length()==0)

                {
                    etStudentName.requestFocus();
                    etStudentName.setError("FIELD CANNOT BE EMPTY");
                }

                else if(!getEditedStudentName.matches("[a-zA-Z ]+"))
                {
                    etStudentName.requestFocus();
                    etStudentName.setError("ENTER ONLY ALPHABETICAL CHARACTER");
                }

                else if(getEditedStudentRoll.length()==0)
                {
                    etRollNumber.requestFocus();
                    etRollNumber.setError("FIELD CANNOT BE EMPTY");
                }
                else if(getEditedStudentRoll.matches("[a-zA-Z ]+")){
                    etRollNumber.requestFocus();
                    etRollNumber.setError("ENTER ONLY INTEGER VALUE");

                }
                else
                {

                    Intent resultEditIntent = new Intent();
                    resultEditIntent.putExtra("editedName",getEditedStudentName);
                    resultEditIntent.putExtra("editedRoll",getEditedStudentRoll);

                    setResult(RESULT_OK,resultEditIntent);
                    finish();

                }

















            }
        });









    }
}
