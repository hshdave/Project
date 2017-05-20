package com.a1694158.harshkumar.myquiz;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TotalScore extends AppCompatActivity {

    TextView txt_correct,txt_wrong,txt_total;
    Button   btn_play,btn_exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total_score);


        Intent intent = getIntent();

            int cor = intent.getIntExtra("correct",0);
            int wro = intent.getIntExtra("wrong",0);

            txt_correct = (TextView) findViewById(R.id.txt_corscore);
            txt_wrong = (TextView) findViewById(R.id.txt_wroscore);
            txt_total = (TextView) findViewById(R.id.txt_totalscore);

            btn_play = (Button) findViewById(R.id.btn_sply);
            btn_exit = (Button) findViewById(R.id.btn_exit);

            int total = cor - wro;

            txt_correct.setText(" "+cor);
            txt_wrong.setText(" "+wro);
            txt_total.setText(" "+total);


        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Quiz.class);
                startActivity(i);
            }
        });

        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
            }
        });

    }
}
