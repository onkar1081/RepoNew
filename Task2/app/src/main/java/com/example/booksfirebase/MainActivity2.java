package com.example.booksfirebase;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
        import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.DatePicker;
        import android.widget.EditText;
import android.widget.Toast;
        import com.google.android.gms.tasks.OnCompleteListener;
        import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
        import java.util.Map;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {
    EditText a1, a2, a3, a4;
    Button btn, btn3, btn4, btn5;
    //    Spinner sp1;
//    ImageButton i1,i2;
    DatabaseReference refernce;
    ArrayList<StudentsInfo> ar = new ArrayList<>();
    StudentsInfo st = new StudentsInfo();
    DatabaseReference fr;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        btn = findViewById(R.id.button8);
        btn3 = findViewById(R.id.button3);
        btn4 = findViewById(R.id.button4);
        btn5 = findViewById(R.id.button5);
        a1 = findViewById(R.id.etBookTitle);
        a2 = findViewById(R.id.etAuthor);
        a3 = findViewById(R.id.etDescription);
        a4 = findViewById(R.id.etCode);
//        a5=findViewById(R.id.etStartDate);
//        a6=findViewById(R.id.etLastDate);
//        i1=findViewById(R.id.imageButton);
//        i2=findViewById(R.id.imageButton2);
//        sp1=findViewById(R.id.spinner);
        String[] arr = {"Select year", "FY", "SY", "TY", "BY"};
        ArrayAdapter<String> ad;
        ad = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, arr);
//        sp1.setAdapter(ad);
        btn.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
//        i1.setOnClickListener(this);
//        i2.setOnClickListener(this);

        fr = FirebaseDatabase.getInstance().getReference();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button8:
                write1();
                break;
//            case R.id.imageButton:
//                Calendar c;
//                c=Calendar.getInstance();
//                int d,m,y;
//                d=c.get(Calendar.DAY_OF_MONTH);
//                m=c.get(Calendar.MONTH);
//                y=c.get(Calendar.YEAR);
//                DatePickerDialog dp;
//                dp=new DatePickerDialog(this,this,y,m,d);
//                dp.getDatePicker().setMaxDate(System.currentTimeMillis());
//                c.set(2000,0,1);
//                dp.getDatePicker().setMinDate(c.getTimeInMillis());
//                dp.show();
//                dp.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                        month++;
//                      String s10=year+"/"+month+"/"+dayOfMonth;
//                      a5.setText(s10);
//                    }
//                });
//                break;
//            case R.id.imageButton2:
//                Calendar c1;
//                c1=Calendar.getInstance();
//                int d1,m1,y1;
//                d1=c1.get(Calendar.DAY_OF_MONTH);
//                m1=c1.get(Calendar.MONTH);
//                y1=c1.get(Calendar.YEAR);
//                DatePickerDialog dp1;
//                dp1=new DatePickerDialog(this,this,y1,m1,d1);
//                dp1.getDatePicker().setMaxDate(System.currentTimeMillis());
//                c1.set(2000,0,1);
//                dp1.getDatePicker().setMinDate(c1.getTimeInMillis());
//                dp1.show();
//                dp1.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                        month++;
//                        String s11=year+"/"+month+"/"+dayOfMonth;
//                        a6.setText(s11);
//                    }
//                });
//                break;
            case R.id.button3:
                update();
                break;
            case R.id.button4:
                delete();
                break;
            case R.id.button5:
                Intent it = new Intent(this, MainActivity3.class);
                //readData();
                //it.putExtra("ArrayList",ar);
                startActivity(it);
                break;

        }
    }

    void write1() {
        int code = Integer.parseInt(a2.getText().toString().trim());
        String title = a1.getText().toString().trim();
        String author = a3.getText().toString().trim();
        String description = a4.getText().toString().trim();
//        String startdate=a5.getText().toString().trim();
//        String  lastdate=a6.getText().toString().trim();
//        String year=sp1.getSelectedItem().toString().trim();
        Log.i("Check1", "data read ");
        st.setCode(code);
        st.setTitle(title);
        st.setAuthor(author);
        st.setDescription(description);
//        st.setStartdate(startdate);
//        st.setLastdate(lastdate);
//        st.setTopic(topic);
//        st.setYear(year);
        Log.i("Check2", "data set ");
        Map<String, Object> map;
        map = new HashMap<>();
        map.put("" + code, st);
        Log.i("Check3", "data put in map ");
        fr.child("books").child("" + code).setValue(st).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(MainActivity2.this, "Record saved ", Toast.LENGTH_SHORT).show();
                    Log.i("Check4", "record saved ");
                } else {
                    Toast.makeText(MainActivity2.this, "Record not saved " + task.getException(), Toast.LENGTH_SHORT).show();
                    Log.i("Check4", "data not saved");
                }
            }
        });
        Log.i("Check5", "write succesfu");

    }

    void delete() {
        int id = Integer.parseInt(a2.getText().toString().trim());
        fr.child("students").child("" + id).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(MainActivity2.this, "Record deleted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity2.this, "Record not deleted", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    void update() {
        int code = Integer.parseInt(a2.getText().toString().trim());
        String title = a1.getText().toString().trim();
        String author = a3.getText().toString().trim();
        String description = a4.getText().toString().trim();
//        String platform=a4.getText().toString().trim();
//        String startdate=a5.getText().toString().trim();
//        String lastdate=a6.getText().toString().trim();
//        String topic=a3.getText().toString().trim();
        StudentsInfo st = new StudentsInfo();
        st.setCode(code);
        st.setTitle(title);
        st.setAuthor(author);
        st.setDescription(description);
//        st.setPlatform(platform);S
//        st.setStartdate(startdate);
//        st.setLastdate(lastdate);
//        st.setTopic(topic);
        //st.setYear(year);
        Map<String, Object> map;
        map = new HashMap<>();
        map.put("/books/" + code, st);
        fr.updateChildren(map).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(MainActivity2.this, "Operation Complete", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity2.this, "Operation not complete", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
