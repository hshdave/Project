package com.a1694158.harshkumar.myquiz;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

/**
 * Created by Harsh on 5/12/2017.
 */

public class Firebasehelper {

    DatabaseReference db;
    ArrayList<Quizquestions> qsd = new ArrayList<>();

    public Firebasehelper(DatabaseReference db)
    {
        this.db = db;
    }

    public void fetchData (DataSnapshot dataSnapshot)
    {
        qsd.clear();

        for (DataSnapshot ds : dataSnapshot.getChildren())
        {
            Quizquestions qs = ds.getValue(Quizquestions.class);
            qsd.add(qs);
        }
    }

    public  ArrayList<Quizquestions> retrive()
    {
        db.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                fetchData(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                fetchData(dataSnapshot);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return qsd;
    }
}
