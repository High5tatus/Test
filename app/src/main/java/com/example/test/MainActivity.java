package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText edt1 = findViewById(R.id.edt1);
        EditText edt2 = findViewById(R.id.edt2);
        EditText edt3 = findViewById(R.id.edt3);

        ListView list = findViewById(R.id.list);

        Button btnRandom = findViewById(R.id.btnRandom);
        Button btnOK = findViewById(R.id.btnOK);

        btnRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num1 = new Random().nextInt(8)+2;
                int num2 = new Random().nextInt(8)+2;
                edt1.setText(String.valueOf(num1));
                edt2.setText(String.valueOf(num2));
            }
        });

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int n1 = Integer.parseInt(edt1.getText().toString());
                int n2 = Integer.parseInt(edt2.getText().toString());
                int n3 = Integer.parseInt(edt3.getText().toString());

                int n4 = n1*n2;

                if(n3==n4){
                    Toast.makeText(MainActivity.this, "정답입니다!!",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "오답입니다!!",Toast.LENGTH_SHORT).show();
                    String[] gugudan = new String[9];
                    for(int i=0; i<9;i++){
                        gugudan[i] = String.valueOf(n1) + "X" + (i+2) + "=" + (n1*(i+2));
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, gugudan);
                    list.setAdapter(adapter);
                }


            }
        });

    }
}