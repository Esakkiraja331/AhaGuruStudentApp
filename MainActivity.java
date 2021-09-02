package com.example.ahagurustudentapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText name,email,s_class,location,dob;
    Button insert,update,delete,view;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        s_class = findViewById(R.id.s_class);
        location = findViewById(R.id.location);
        dob = findViewById(R.id.dob);

        insert = findViewById(R.id.btnInsert);
        update = findViewById(R.id.btnUptate);
        delete = findViewById(R.id.btnDelete);
        view = findViewById(R.id.btnView);
        DB = new DBHelper(this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String emailTxt = email.getText().toString();
                String s_classTxt = s_class.getText().toString();
                String locationTxt = location.getText().toString();
                String dobTxt = dob.getText().toString();

                Boolean checkinsertdata = DB.insertuserdata(nameTXT,emailTxt,s_classTxt,locationTxt,dobTxt);
                if(checkinsertdata==true)
                    Toast.makeText(MainActivity.this,"New Entry Inserted",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this,"New Entry Not Inserted",Toast.LENGTH_SHORT).show();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String emailTxt = email.getText().toString();
                String s_classTxt = s_class.getText().toString();
                String locationTxt = location.getText().toString();
                String dobTxt = dob.getText().toString();

                Boolean checkupdatedata = DB.updateuserdata(nameTXT,emailTxt,s_classTxt,locationTxt,dobTxt);
                if(checkupdatedata==true)
                    Toast.makeText(MainActivity.this,"Entry Updated",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this,"New Entry Not Updated",Toast.LENGTH_SHORT).show();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                Boolean checkdeletdata = DB.deletedata(nameTXT);
                if(checkdeletdata==true)
                    Toast.makeText(MainActivity.this,"Entry Deleted",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this,"Entry Not Deleted",Toast.LENGTH_SHORT).show();
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getdata();
                if(res.getCount()==0){
                    Toast.makeText(MainActivity.this,"No Entry Exists",Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("Name :"+res.getString(0)+"\n");
                    buffer.append("Email :"+res.getString(1)+"\n");
                    buffer.append("S_Class :"+res.getString(2)+"\n");
                    buffer.append("Location :"+res.getString(3)+"\n");
                    buffer.append("Date Of Birth :"+res.getString(4)+"\n");

                }
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("User Enteries");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
    }
}