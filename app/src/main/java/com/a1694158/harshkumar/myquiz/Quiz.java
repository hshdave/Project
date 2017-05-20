package com.a1694158.harshkumar.myquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class Quiz extends AppCompatActivity {

    TextView txt_que,score,btn_finish;
    RadioButton chA,chB,chC,chD,sel;
    Button btn_nxt;

    private int index;
    int select1;

    int scr = 0;
    int dsr = 0;
    RadioGroup rg;

    ArrayList<Quizquestions> question;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Que");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);


        rg = (RadioGroup) findViewById(R.id.radioGroup);

        txt_que = (TextView) findViewById(R.id.txt_qque);
        score = (TextView) findViewById(R.id.score);

        chA = (RadioButton) findViewById(R.id.rd_chA);
        chB = (RadioButton) findViewById(R.id.rd_chB);
        chC = (RadioButton) findViewById(R.id.rd_chC);
        chD = (RadioButton) findViewById(R.id.rd_chD);


        btn_nxt =(Button) findViewById(R.id.btn_qnxt);
        btn_finish = (Button) findViewById(R.id.btn_fns);

        btn_nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(question != null && !question.isEmpty()) {

                    if(rg.getCheckedRadioButtonId()  == -1)
                    {
                        Toast.makeText(getApplicationContext(),"Please Select Choice!",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        if (checkCorrect()) {
                            Toast.makeText(getApplicationContext(), "Correct!", Toast.LENGTH_SHORT).show();
                            index++;
                            scr++;
                        } else if (!checkCorrect()) {
                            Toast.makeText(getApplicationContext(), "Wrong!", Toast.LENGTH_SHORT).show();
                            index++;
                            dsr++;
                        }

                        score.setText("Your Score is:" + scr);
                        dispQue(index);
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Please Wait For Question",Toast.LENGTH_SHORT).show();
                }

            }
        });


        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                question = new ArrayList<Quizquestions>();

                for (DataSnapshot qs : dataSnapshot.getChildren()) {
                    question.add(qs.getValue(Quizquestions.class));
                }

                index = 0;
                Collections.shuffle(question);
                dispQue(index);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scoreBoard();
            }
        });
    }


    private void dispQue(int index)
    {
        if(question != null && !question.isEmpty()) {


            if (index < question.size()) {
                txt_que.setText(question.get(index).getQuestion());
                chA.setText(question.get(index).getChA());
                chB.setText(question.get(index).getChB());
                chC.setText(question.get(index).getChC());
                chD.setText(question.get(index).getChD());
            } else {
                Toast.makeText(getApplicationContext(), "Quiz is Done! No More Questions", Toast.LENGTH_SHORT).show();
                scoreBoard();
            }
        }

    }

    private boolean checkCorrect()
    {
        select1 = rg.getCheckedRadioButtonId();
        sel =(RadioButton) findViewById(select1);


            if(index < question.size()) {
              {
                      if (sel.getText().equals(question.get(index).getCorrectAns())) {
                          return true;
                      } else {
                          return false;

                            }
                }
            }
            else
            {
                Toast.makeText(getApplicationContext(),"Correct Done!",Toast.LENGTH_SHORT).show();
            }

        return false;
    }

    private void scoreBoard()
    {
        Intent i = new Intent(getApplicationContext(),TotalScore.class);
        i.putExtra("correct",scr);
        i.putExtra("wrong",dsr);
        startActivity(i);
        finish();
    }


}
