package com.example.booksfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity3 extends AppCompatActivity  {
    ListView lv;
    DatabaseReference fr1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        lv=findViewById(R.id.newlv);
        fr1= FirebaseDatabase.getInstance().getReference();
        ValueEventListener listener=new ValueEventListener() {


            String data="";
            ArrayList<StudentsInfo> ar=new ArrayList<>();

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot sn:snapshot.getChildren()){
                    for(DataSnapshot sn1:sn.getChildren()){
                        StudentsInfo st1=sn1.getValue(StudentsInfo.class);
                        ar.add(st1);
                        data+=st1.getCode()+" "+st1.getTitle()+ " "+st1.getAuthor()
                                +" "+st1.getDescription();
                    }
                }
                String[] arr=new String[ar.size()];
                int i=0;
                for (StudentsInfo st1:ar){
                    arr[i]=st1.getCode()+" "+st1.getTitle()+ " "+st1.getAuthor()
                            +" "+st1.getDescription();
                    i++;
                }
                ArrayAdapter<String> ad;
                ad = new ArrayAdapter<String>(MainActivity3.this, R.layout.support_simple_spinner_dropdown_item, arr);
                lv.setAdapter(ad);

                //ar1.clear();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        fr1.addValueEventListener(listener);

    }

    }