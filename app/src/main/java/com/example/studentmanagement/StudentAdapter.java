package com.example.studentmanagement;

import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import android.widget.TextView;


import java.util.ArrayList;


public class StudentAdapter  extends  RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {
    private ArrayList<StudentItem> mStudentList;
    private OnItemClickListener mListener;


    public interface OnItemClickListener
    {
        // function for responding on view in recycler view

        void onItemClick(int position);
        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener)
    {
        mListener = listener;
    }







    public static class StudentViewHolder extends RecyclerView.ViewHolder {

        public TextView nameTextView;
        public TextView rollNumberTextView;
        public ImageView mDeleteImage;



        public StudentViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);

            nameTextView = itemView.findViewById(R.id.nameid);
            rollNumberTextView = itemView.findViewById(R.id.rollnumberid);
            mDeleteImage =itemView.findViewById(R.id.imagedelete);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }




                }
            });


            mDeleteImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {



                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onDeleteClick(position);

                        }
                    }





                }
            });




        }
    }

    public StudentAdapter(ArrayList<StudentItem> studentList) {
        mStudentList = studentList;
    }

    @Override
    public StudentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_list, parent, false);
        StudentViewHolder svh = new StudentViewHolder(v,mListener);
        return svh;
    }

    @Override

    public void onBindViewHolder(StudentViewHolder holder, int position) {
        StudentItem currentItem = mStudentList.get(position);


        holder.nameTextView.setText(currentItem.getName());
        holder.rollNumberTextView.setText(Integer.toString(currentItem.getRollNumber()));













    }

    @Override
    public int getItemCount() {
        return mStudentList.size();
    }
}