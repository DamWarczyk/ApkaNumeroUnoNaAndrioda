package com.example.apkanumerounonaandrioda;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;



import java.util.List;


public class MyGradeRecyclerViewAdapter extends RecyclerView.Adapter<MyGradeRecyclerViewAdapter.ViewHolder> {

    private final List<GradeModel> mValues;

    public MyGradeRecyclerViewAdapter(FragmentActivity activity, List<GradeModel> items) {
        LayoutInflater layoutInflater = activity.getLayoutInflater();
        mValues = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_grade_row, parent, false);
        return new ViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getName().setText(mValues.get(position).getSubjecName());
        holder.getRadioGroup().setTag(position);

        switch(mValues.get(position).getValue()){
            case 2:
                holder.getRadioGroup().check(R.id.radioButton2);
                break;
            case 3:
                holder.getRadioGroup().check(R.id.radioButton3);
                break;
            case 4:
                holder.getRadioGroup().check(R.id.radioButton4);
                break;
            case 5:
                holder.getRadioGroup().check(R.id.radioButton5);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements RadioGroup.OnCheckedChangeListener {
        public TextView mIdView;
        public RadioGroup radioGroup;

        public ViewHolder(@NonNull View view) {
            super(view);
            mIdView = view.findViewById(R.id.subject_name);
            radioGroup = view.findViewById(R.id.groupSubjectButton);

            radioGroup.setOnCheckedChangeListener((rG, i)-> {
                int index = (int) radioGroup.getTag();
                switch (i){
                    case R.id.radioButton2:
                        mValues.get(index).setValue(2);
                        break;
                    case R.id.radioButton3:
                        mValues.get(index).setValue(3);
                        break;
                    case R.id.radioButton4:
                        mValues.get(index).setValue(4);
                        break;
                    case R.id.radioButton5:
                        mValues.get(index).setValue( 5);
                        break;
                }
            });
        }

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            radioGroup.check(checkedId);
        }

        public RadioGroup getRadioGroup() {
            return radioGroup;
        }

        public TextView getName() {
            return mIdView;
        }
    }

    public List<GradeModel> getmValues() {
        return mValues;
    }

}