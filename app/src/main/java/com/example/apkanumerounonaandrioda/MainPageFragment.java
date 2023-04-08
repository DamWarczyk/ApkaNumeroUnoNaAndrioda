package com.example.apkanumerounonaandrioda;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.apkanumerounonaandrioda.databinding.FragmentMainPageBinding;

import java.util.Objects;


public class MainPageFragment extends Fragment {
    private FragmentMainPageBinding binding;
    private double mean = 0.0;

    public MainPageFragment() {
        // Required empty public constructor
    }

    public static MainPageFragment newInstance() {
        MainPageFragment fragment = new MainPageFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentMainPageBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NavController navigation = NavHostFragment.findNavController(MainPageFragment.this);

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean available = true;
                if(MainPageFragment.this.mean > 0.0 && binding.nameEditText.getText().toString().isEmpty()){
                    binding.nameEditText.setError("Imie jest obowiązkowe");
                    available = false;
                }

                if(MainPageFragment.this.mean > 0.0 && binding.surnameEditText.getText().toString().isEmpty()) {
                    binding.surnameEditText.setError("Nazwisko jest obowiązkowe");
                    available = false;
                }

                if(MainPageFragment.this.mean > 0.0 && binding.gradeEditText.getText().toString().isEmpty()){
                    binding.gradeEditText.setError("Ilość ocen jest wymagana");
                    available = false;
                }

                if(available) {
                    if(MainPageFragment.this.mean == 0.0 && binding.gradeEditText.getError() == null){
                        Bundle bundle = new Bundle();
                        String text = binding.gradeEditText.getText().toString();
                        if(!text.isEmpty()){
                            int gradesNumber = Integer.parseInt(text);
                            bundle.putInt("gradesNumber", gradesNumber);
                            navigation.navigate(R.id.action_mainPageFragment_to_gradeListFragment, bundle);
                        }
                    } else if(MainPageFragment.this.mean < 3.0 && MainPageFragment.this.mean >= 2.0) {
                        Toast.makeText(MainPageFragment.this.getActivity(), "Niestety nie udało się", Toast.LENGTH_SHORT).show();
                        MainPageFragment.this.getActivity().finishAffinity();
                    } else if (MainPageFragment.this.mean >= 3.0) {
                        Toast.makeText(MainPageFragment.this.getActivity(), "Gratulacje!!!", Toast.LENGTH_SHORT).show();
                        MainPageFragment.this.getActivity().finishAffinity();
                    }
            }}
        });
    }
}