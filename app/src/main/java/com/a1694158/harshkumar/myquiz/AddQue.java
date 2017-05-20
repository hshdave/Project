package com.a1694158.harshkumar.myquiz;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class AddQue extends AppCompatActivity {

    EditText edt_que,edt_chA,edt_chB,edt_chC,edt_chD,edt_correctAns;
    Button btn_add;

    int count,mx;

    ArrayList<Integer> ch;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Que");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_que);

        edt_que = (EditText) findViewById(R.id.edt_que);
        edt_chA = (EditText) findViewById(R.id.edt_chA);
        edt_chB = (EditText) findViewById(R.id.edt_chB);
        edt_chC = (EditText) findViewById(R.id.edt_chC);
        edt_chD = (EditText) findViewById(R.id.edt_chD);
        edt_correctAns = (EditText) findViewById(R.id.edt_correctAns);

        btn_add = (Button) findViewById(R.id.btn_add);

        count = 0;


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                ch  = new ArrayList<Integer>();
                for(DataSnapshot qs : dataSnapshot.getChildren())
                {
                    ch.add(Integer.parseInt(qs.getKey()));
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String que,chA,chB,chC,chD,correctAns;

                que = edt_que.getText().toString();
                chA = edt_chA.getText().toString();
                chB = edt_chB.getText().toString();
                chC = edt_chC.getText().toString();
                chD = edt_chD.getText().toString();
                correctAns = edt_correctAns.getText().toString();



               if(ch != null && !ch.isEmpty())
               {
                   mx = Collections.max(ch);
                   mx++;
               }else
               {
                   mx = 0;
               }

                String cnt = String.valueOf(mx);
                myRef.child(cnt).setValue(new Quizquestions(que,chA,chB,chC,chD,correctAns));
                Toast.makeText(getApplicationContext(),"Quiz Quettion Added successfully!",Toast.LENGTH_SHORT).show();

                DialogInterface.OnClickListener dg =  new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which)
                        {
                            case DialogInterface.BUTTON_POSITIVE:
                                clearAll();
                                break;
                            case DialogInterface.BUTTON_NEGATIVE:
                                Intent i = new Intent(AddQue.this,MainActivity.class);
                                startActivity(i);
                                break;

                        }
                    }
                };

                AlertDialog.Builder build = new AlertDialog.Builder(AddQue.this);

                    build.setMessage("Do you want to Add More Questiions?").setPositiveButton("Yes",dg).setNegativeButton("No",dg).show();

            }
        });

    }
    public void clearAll()
    {
        edt_que.setText("");
        edt_chA.setText("");
        edt_chB.setText("");
        edt_chC.setText("");
        edt_chD.setText("");
        edt_correctAns.setText("");
    }
}
