package com.a1694158.harshkumar.myquiz;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_add,btn_del,btn_edt,btn_ext;
        ImageButton btn_ply;

        btn_ply = (ImageButton) findViewById(R.id.btn_ply);
        btn_add = (Button) findViewById(R.id.btn_add);
        btn_del = (Button) findViewById(R.id.btn_del);
        btn_edt = (Button) findViewById(R.id.btn_edit);
        btn_ext = (Button) findViewById(R.id.btn_ext);

        btn_ply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            intentCall(MainActivity.this,Quiz.class);

            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intentCall(MainActivity.this,AddQue.class);

            }
        });

        btn_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intentCall(MainActivity.this,delete_que.class);

            }
        });

        btn_edt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intentCall(MainActivity.this,EditList.class);

            }
        });


        btn_ext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
            }
        });




    }

    private void intentCall(Context c1,Class c2)
    {
        if(checkNet())
        {
            Intent i = new Intent(c1,c2);
            startActivity(i);
        }
        else {
            Toast.makeText(MainActivity.this,"Please Check Your Network Connection !", Toast.LENGTH_SHORT).show();
        }
    }


    private boolean checkNet()
    {
        ConnectivityManager cm =(ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }
}
