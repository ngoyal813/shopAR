package com.example.shopar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shopar.Prevalent.Prevalent;
import com.example.shopar.models.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rey.material.widget.CheckBox;

import io.paperdb.Paper;

public class loginActivity extends AppCompatActivity {

    private Button btn_login;
    private EditText Inputname,Inputpassword,Inputphone;
    private TextView tv_iamanadmin;
    private TextView tv_notanadmin;
    private String parentDbname = "Users";
    private CheckBox rememberme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn_login = findViewById(R.id.btn_login);
        Inputpassword = findViewById(R.id.et_loginpassword);
        Inputphone = findViewById(R.id.et_loginphone);
        tv_iamanadmin = findViewById(R.id.tv_iamanadmin);
        tv_notanadmin =  findViewById(R.id.tv_notanadmin);
        Inputname = findViewById(R.id.et_loginname);
        rememberme = findViewById(R.id.cb_rememberme);
        btn_login.setEnabled(false);
        Paper.init(this);

        Inputname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkinputs();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        Inputphone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkinputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Inputpassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkinputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allowacesstoaccount();
            }
        });

        tv_iamanadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                parentDbname ="Admins";
                tv_iamanadmin.setVisibility(View.GONE);
                tv_notanadmin.setVisibility(View.VISIBLE);
            }
        });

        tv_notanadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parentDbname = "Users";
                tv_iamanadmin.setVisibility(View.VISIBLE);
                tv_notanadmin.setVisibility(View.GONE);
            }
        });
    }

    private void allowacesstoaccount() {

        if(rememberme.isChecked()){
            Paper.book().write(Prevalent.userphonekey,Inputphone.getText().toString());
            Paper.book().write(Prevalent.userpasswordkey,Inputpassword.getText().toString());
        }
        final DatabaseReference rootref;
        rootref = FirebaseDatabase.getInstance("https://shopar-497ee-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference();
        rootref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.child(parentDbname).child(Inputphone.getText().toString()).exists())
                {
                    Users userdata = snapshot.child(parentDbname).child(Inputphone.getText().toString()).getValue(Users.class);
                    if(userdata.getPhone().equals(Inputphone.getText().toString())){
                        if(userdata.getPassword().equals(Inputpassword.getText().toString())){
                            if(parentDbname.equals("Admins")){
                                Intent adminintent = new Intent(loginActivity.this,adminActivity.class);
                                startActivity(adminintent);
                            }
                            else if(parentDbname.equals("Users")){
                                Intent loginintent = new Intent(loginActivity.this,homeActivity.class);
                                startActivity(loginintent);
                            }

                        }
                    }
                }else{
                    Toast.makeText(loginActivity.this,"User Doesn't Exists",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    private void checkinputs() {
        if (!TextUtils.isEmpty(Inputname.getText())) {
            if (!TextUtils.isEmpty(Inputphone.getText())) {
                if (!TextUtils.isEmpty(Inputpassword.getText())) {
                    btn_login.setEnabled(true);
                } else {
                    btn_login.setEnabled(false);
                }
            } else {
                btn_login.setEnabled(false);
            }
        }else{
            btn_login.setEnabled(false);
        }
    }
}