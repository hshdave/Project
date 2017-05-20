package com.a1694158.harshkumar.myquiz;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.xml.sax.DTDHandler;

import java.util.ArrayList;

public class delete_que extends AppCompatActivity {

    DatabaseReference db;
    Firebasehelper fh;

    ListView lsv;
    Listviewadapter lsd;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_que);

        lsv = (ListView) findViewById(R.id.que_lst);

       db = FirebaseDatabase.getInstance().getReference();
        fh = new Firebasehelper(db);

        lsd = new Listviewadapter(fh.retrive(),this);
        lsv.setAdapter(lsd);

    }
}
