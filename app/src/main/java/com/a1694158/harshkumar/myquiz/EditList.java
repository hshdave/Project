package com.a1694158.harshkumar.myquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditList extends AppCompatActivity {

    DatabaseReference db;
    Firebasehelper fh;

    ListView lsv;
    Listviewadapteredit lsd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_list);


        lsv = (ListView) findViewById(R.id.edt_lst);

        db = FirebaseDatabase.getInstance().getReference();
        fh = new Firebasehelper(db);

        lsd = new Listviewadapteredit(fh.retrive(),this);
        lsv.setAdapter(lsd);
    }
}
