package com.example.test2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String gender = "";
    String blood = "";

    double height, weight;
    double bmi = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("몸 상태 측정");

        //키 체중
        EditText edt1 = findViewById(R.id.edt1);
        EditText edt2 = findViewById(R.id.edt2);

        //성별
        RadioButton rb1 = findViewById(R.id.rb1);
        RadioButton rb2 = findViewById(R.id.rb2);

        //혈액형
        Spinner spinner = findViewById(R.id.sp);

        //습관
        CheckBox chk1 = findViewById(R.id.chk1);
        CheckBox chk2 = findViewById(R.id.chk2);
        CheckBox chk3 = findViewById(R.id.chk3);

        //결과창
        TextView tv1 = findViewById(R.id.tv1);
        TextView tv2 = findViewById(R.id.tv2);

        //확인
        Button btnCheck = findViewById(R.id.btnCheck);

        //사진



        rb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton rb = (RadioButton) v;
                gender = rb.getText().toString();
            }
        });
        rb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton rb = (RadioButton) v;
                gender = rb.getText().toString();
            }
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.blood_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                blood = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ArrayList<Integer> mThumblds = new ArrayList<Integer>();

                if(edt1.getText().toString().equals("")||edt2.getText().toString().equals("") )
                {
                    tv2.setText("2.신체질량지수는 ???입니다!");

                    new AlertDialog.Builder(MainActivity.this).setTitle("키와 체중").setView(getLayoutInflater().inflate(R.layout.custom_dialog, null)).show();

                }
                else
                {
                    height = Double.parseDouble((edt1.getText().toString()));
                    weight = Double.parseDouble((edt2.getText().toString()));

                    bmi = weight/ ((height/100) * (height/100));

                    tv2.setText("2.신체질량지수는 " + Math.round(bmi*10)/10F + "입니다.");
                }
                if (blood.equals("") || gender.equals("")){
                    tv1.setText("1.?형 ??입니다!");
                    return;
                }
                else{
                    tv1.setText("1."+blood+"형 " + gender + "입니다!");
                }

                if(chk1.isChecked()){
                    mThumblds.add(R.drawable.drinking);
                }
                if(chk2.isChecked()){
                    mThumblds.add(R.drawable.ciga);
                }
                if(chk3.isChecked()){
                    mThumblds.add(R.drawable.running);
                }

                Gallery ga1 = (Gallery)findViewById(R.id.ga1);
                if(chk1.isChecked() || chk2.isChecked() || chk3.isChecked()){
                    ga1.setAdapter(new ImageAdapter(MainActivity.this, mThumblds));
                }
                else {
                    ga1.removeAllViewsInLayout();
                }


            }
        });






    }
}