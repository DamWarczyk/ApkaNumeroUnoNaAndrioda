package com.example.apkanumerounonaandrioda;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.text.Editable;
import android.text.TextWatcher;
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

        MeanViewModel viewModel = new ViewModelProvider(requireActivity()).get(MeanViewModel.class);

        if (viewModel.getMean() != null) {
            mean = viewModel.getMean();
        }

        binding.meanValueTextLabel.setText(String.format(String.valueOf(mean)));
        if (mean == 0.0) {
            binding.button.setText(String.format(getString(R.string.grade)));
            binding.meanValueTextLabel.setVisibility(View.INVISIBLE);
            binding.meanTextLabel.setVisibility(View.INVISIBLE);
        } else if (mean < 3.0 && mean >= 2.0) {
            binding.button.setText(String.format(getString(R.string.not_this_time)));
            binding.meanValueTextLabel.setVisibility(View.VISIBLE);
            binding.meanTextLabel.setVisibility(View.VISIBLE);
        } else if (mean > 3.0) {
            binding.button.setText(String.format(getString(R.string.super_nice)));
            binding.meanValueTextLabel.setVisibility(View.VISIBLE);
            binding.meanTextLabel.setVisibility(View.VISIBLE);
        }

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean available = true;
                if (MainPageFragment.this.mean > 0.0 && binding.nameEditText.getText().toString().isEmpty()) {
                    binding.nameEditText.setError("Imie jest obowiązkowe");
                    available = false;
                }

                if (MainPageFragment.this.mean > 0.0 && binding.surnameEditText.getText().toString().isEmpty()) {
                    binding.surnameEditText.setError("Nazwisko jest obowiązkowe");
                    available = false;
                }

                if (MainPageFragment.this.mean > 0.0 && binding.gradeEditText.getText().toString().isEmpty()) {
                    binding.gradeEditText.setError("Ilość ocen jest wymagana");
                    available = false;
                }

                if (!binding.gradeEditText.getText().toString().isEmpty()){
                String gradeText = binding.gradeEditText.getText().toString();
                if (MainPageFragment.this.mean > 0.0 && 5 < Integer.parseInt(gradeText) || Integer.parseInt(gradeText) > 15) {
                    binding.gradeEditText.setError("Podaj numer z przedziału");
                    available = false;
                }
                }


                if (available) {
                    if (MainPageFragment.this.mean == 0.0 && binding.gradeEditText.getError() == null) {
                        Bundle bundle = new Bundle();
                        String text = binding.gradeEditText.getText().toString();
                        if (!text.isEmpty()) {
                            int gradesNumber = Integer.parseInt(text);
                            bundle.putInt("gradesNumber", gradesNumber);
                            navigation.navigate(R.id.action_mainPageFragment_to_gradeListFragment, bundle);
                        }
                    } else if (MainPageFragment.this.mean < 3.0 && MainPageFragment.this.mean >= 2.0) {
                        Toast.makeText(MainPageFragment.this.getActivity(), "Niestety nie udało się", Toast.LENGTH_SHORT).show();
                        MainPageFragment.this.getActivity().finishAffinity();
                    } else if (MainPageFragment.this.mean >= 3.0) {
                        Toast.makeText(MainPageFragment.this.getActivity(), "Gratulacje!!!", Toast.LENGTH_SHORT).show();
                        MainPageFragment.this.getActivity().finishAffinity();
                    }
                }
            }
        });

        binding.nameEditText.addTextChangedListener(
                new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        if (charSequence.toString().isEmpty()) {
                            binding.nameEditText.setError("Imie jest obowiązkowe");
                        } else {
                            binding.nameEditText.setError(null);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                        if (binding.nameEditText.getText().toString().isEmpty()) {
                            binding.nameEditText.setError("Imie jest obowiązkowe");
                        } else {
                            binding.nameEditText.setError(null);
                        }
                    }
                }
        );


        binding.surnameEditText.addTextChangedListener(
                new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        if (charSequence.toString().isEmpty()) {
                            binding.surnameEditText.setError("Nazwisko jest obowiązkowe");
                        } else {
                            binding.surnameEditText.setError(null);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                        if (binding.surnameEditText.getText().toString().isEmpty()) {
                            binding.surnameEditText.setError("Nazwisko jest obowiązkowe");
                        } else {
                            binding.surnameEditText.setError(null);
                        }
                    }
                }
        );

        binding.gradeEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().isEmpty()) {
                    binding.gradeEditText.setError("Ocena jest obowiązkowa");
                } else {
                    if (5 >= Integer.parseInt(charSequence.toString()) || Integer.parseInt(charSequence.toString()) >= 15) {
                        binding.gradeEditText.setError("Podaj numer z przedziału");
                    } else {
                        binding.gradeEditText.setError(null);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().isEmpty()) {
                    binding.gradeEditText.setError("Ocena jest obowiązkowa");
                } else {
                    if (5 > Integer.parseInt(editable.toString()) || Integer.parseInt(editable.toString()) > 15) {
                        binding.gradeEditText.setError("Podaj numer z przedziału");
                    } else {
                        binding.gradeEditText.setError(null);
                    }
                }
            }
        });

    }
}