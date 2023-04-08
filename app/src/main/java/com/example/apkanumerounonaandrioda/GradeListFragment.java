package com.example.apkanumerounonaandrioda;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.apkanumerounonaandrioda.databinding.FragmentGradeListBinding;


import java.util.ArrayList;
import java.util.Objects;

/**
 * A fragment representing a list of Items.
 */
public class GradeListFragment extends Fragment {
    private @NonNull FragmentGradeListBinding binding;
    ArrayList<GradeModel> grades = new ArrayList<>();
    private Integer numberSubject;

    public GradeListFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentGradeListBinding.inflate(inflater, container, false);


        assert getArguments() != null;
        numberSubject = getArguments().getInt("gradesNumber", 0);

        super.onCreate(savedInstanceState);

        String[] gradeNames = getResources().getStringArray(R.array.subjectsList);
        for(int i = 0; i < numberSubject; i++){
            grades.add(new GradeModel(gradeNames[i], 2));
        }

        MyGradeRecyclerViewAdapter gradesAdapter = new MyGradeRecyclerViewAdapter(this.requireActivity(), grades);

        RecyclerView gradeList = binding.list;
        gradeList.setAdapter(gradesAdapter);
        gradeList.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        binding.buttonGradeList.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        double mean = 0;
                        Bundle bundle = new Bundle();
                        for(GradeModel g: grades){
                            mean += g.getValue();
                        }
                        mean /= grades.size();
                        bundle.putDouble("mean_score", mean);
                        NavController navigation = NavHostFragment.findNavController(GradeListFragment.this);
                        Objects.requireNonNull(navigation.getPreviousBackStackEntry()).getSavedStateHandle().set("mean", mean);
                        navigation.popBackStack();
                    }
                }
        );

        return binding.getRoot();
    }


}