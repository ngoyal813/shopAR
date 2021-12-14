package com.example.shopar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shopar.Prevalent.Prevalent;
import com.example.shopar.models.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity {

    private Button getstarted;
    private TextView alreadyhaveanaccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getstarted = findViewById(R.id.btn_getstarted);
        alreadyhaveanaccount = findViewById(R.id.tv_alreadyhaveanaccount);

        Paper.init(this);

        getstarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerintent = new Intent(MainActivity.this,registerActivity.class);
                startActivity(registerintent);
            }
        });

        alreadyhaveanaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginintent = new Intent(MainActivity.this,loginActivity.class);
                startActivity(loginintent);
            }
        });

        String userphonekey = Paper.book().read(Prevalent.userphonekey);
        String userpassword = Paper.book().read(Prevalent.userpasswordkey);

        if(userphonekey !="" && userpassword != ""){
            if(!TextUtils.isEmpty(userphonekey) && !TextUtils.isEmpty(userpassword)){
                Allowaccess(userpassword,userphonekey);
            }
        }

    }

    private void Allowaccess(String userpassword, String userphonekey) {

        final DatabaseReference rootref;
        rootref = FirebaseDatabase.getInstance("https://shopar-497ee-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference();
        rootref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.child("Users").child(userphonekey).exists())
                {
                    Users userdata = snapshot.child("Users").child(userphonekey).getValue(Users.class);
                    if(userdata.getPhone().equals(userphonekey)){
                        if(userdata.getPassword().equals(userpassword)){
                            Intent loginintent = new Intent(MainActivity.this,homeActivity.class);
                            startActivity(loginintent);
                        }
                    }
                }else{
//                    Toast.makeText(MainActivity.this,"user doesn't exists",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

    }
}