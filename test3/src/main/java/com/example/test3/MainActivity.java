package com.example.test3;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends Activity
{
    EditText[] number = new EditText[6];
    Integer[] lots = {R.id.lotto0,R.id.lotto1,R.id.lotto2,R.id.lotto3,R.id.lotto4,R.id.lotto5};
    Integer[] hosimg = {R.drawable.img0 ,R.drawable.img1,R.drawable.img2,R.drawable.img3,R.drawable.img4,R.drawable.img5,R.drawable.img6,R.drawable.img7,R.drawable.img8,R.drawable.img9,R.drawable.img10,R.drawable.img11,R.drawable.img12,R.drawable.img13,R.drawable.img14,R.drawable.img15};
    Button btn;
    ImageView hos;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.startBtn);
        hos = findViewById(R.id.hos);



        for(int i = 0; i<6; i++){
            number[i] = (EditText)findViewById(lots[i]);
        }

        //로또 담을 배열
        int Lotto[] = new int[6];

        btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=0; i<Lotto.length; i++){
                    Lotto[i] = (int)(Math.random()*45)+1;

                    for(int j=0; j<i; j++){
                        if(Lotto[i] == Lotto[j]){
                            i--;
                            break;
                        }//중복값 제거
                    }
                }

                for(int i=0; i<Lotto.length;i++){
                    number[i].setText(""+Lotto[i]);
                }

                new Thread(){
                    public void run(){
                        for(int i=0; i<hosimg.length;i++){
                            int finalI = i;
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    hos.setImageResource(hosimg[finalI]);
                                }
                            });
                            SystemClock.sleep(20);
                        }
                    }
                }.start();

            }
        });
    }
}