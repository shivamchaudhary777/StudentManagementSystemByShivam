package com.example.studentmanagement;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity  {
    private RecyclerView mRecyclerView;
    private StudentAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private int position1;
    private ArrayList<StudentItem> studentList = new ArrayList<>();
    Switch btnswitch;
    Button btnAddStudent;
    TextView tvNoData;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        //  text view showing notification when there is no added data in array list
        tvNoData =findViewById(R.id.nodata);
        if(studentList.size()==0)
        {
            tvNoData.setText("NO STUDENT ADDED");
        }









        // creating recycler view

        mRecyclerView = findViewById(R.id.recyclerview);

        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new StudentAdapter(studentList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);






        //  dialog box created and performing the action
        mAdapter.setOnItemClickListener(new StudentAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(final int position) {

                final String[] items={ "VIEW","EDIT"};

                AlertDialog.Builder mbuilder =new AlertDialog.Builder(MainActivity.this);
                mbuilder.setTitle("CHOOSE ACTION TO PERFORM");
                mbuilder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                       String selectedItem =items[which];
                       dialog.dismiss();

                       switch(which){

                           // viewing the student data
                           case 0:
                               Intent viewIntent = new Intent(MainActivity.this, ViewStudentActivity.class);
                               viewIntent.putExtra("viewName",studentList.get(position).getName());
                               viewIntent.putExtra("viewRollNumber",studentList.get(position).getRollNumber());
                               startActivity(viewIntent);




                               break;

                             // editing the student data
                           case 1:
                               position1=position;
                            Intent editIntent =new Intent(MainActivity.this,EditStudentActivity.class);
                            startActivityForResult(editIntent,2);


                            break;







                       }







                    }
                });

                AlertDialog mAlert =mbuilder.create();
                mAlert.show();







            }


             // performing delete on click image
            @Override
            public void onDeleteClick(int position)
            {
                studentList.remove(position);
                mAdapter.notifyItemRemoved(position);




                  //  if array list is empty then text view showing message
                if(studentList.size()==0)
                {
                    tvNoData.setVisibility(View.VISIBLE);

                }





            }
        });






















       // shifting to add student page

        btnAddStudent = findViewById(R.id.addstudent);

        btnAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent addIntent = new Intent(MainActivity.this, AddStudentActivity.class);
                startActivityForResult(addIntent, 1);


            }
        });




        // switch button to shift from recycler to grid view

         btnswitch =findViewById(R.id.switchbtn);
        btnswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked == true){

                    mRecyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this,2));

                }
                else
                    {

                        mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    }





            }
        });



    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        // onActivityResult of add
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {

                String getStudentName = data.getStringExtra("etStudentName");
                int getRollNumber =  Integer.parseInt(data.getStringExtra("etRollNumber"));
                studentList.add(new StudentItem (getStudentName,getRollNumber));

                if(tvNoData.getVisibility() == View.VISIBLE)
                {
                    tvNoData.setVisibility(View.INVISIBLE);

                }

            }


        }

       // onActivity result for edit
        if(requestCode ==2)
        {
            if (resultCode == RESULT_OK)
            {
                String getEditedStudentName = data.getStringExtra("editedName");
                int getEditedStudentRoll =  Integer.parseInt(data.getStringExtra("editedRoll"));
                studentList.set(position1 ,new StudentItem(getEditedStudentName,getEditedStudentRoll));
                mAdapter.notifyDataSetChanged();


            }




        }






    }
















    //  inflate menu xml
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater =getMenuInflater();
        inflater.inflate(R.menu.sorting_menu,menu);
        return true;



    }

         // select menu items from drop down menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {

            case R.id.menunameid:

                Collections.sort(studentList,new SortingByName());
                mAdapter.notifyDataSetChanged();

                break;


            case R.id.menurollnoid:
                 Collections.sort(studentList,new SortingByRollNumber());
                 mAdapter.notifyDataSetChanged();



                break;

        }


        return super.onOptionsItemSelected(item);





    }




}