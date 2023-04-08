package com.example.apkanumerounonaandrioda;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.apkanumerounonaandrioda.databinding.FragmentGradeListBinding;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent gradesActivity = new Intent(this, GradeActivity.class);


    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        EditText name = (EditText) findViewById(R.id.nameEditText);
        EditText surname = (EditText) findViewById(R.id.surnameEditText);
        EditText grade = (EditText) findViewById(R.id.gradeEditText);
        outState.putString("name", name.getText().toString());
        outState.putString("surname", surname.getText().toString());
        outState.putString("grade", grade.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        EditText name = (EditText) findViewById(R.id.nameEditText);
        EditText surname = (EditText) findViewById(R.id.surnameEditText);
        EditText grade = (EditText) findViewById(R.id.gradeEditText);
        name.setText(savedInstanceState.getString("name"));
        surname.setText(savedInstanceState.getString("surname"));
        grade.setText(savedInstanceState.getString("grade"));
    }


}