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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class registerActivity extends AppCompatActivity {

    private Button btn_register;
    private EditText Inputname, Inputpassword,Inputphone,Inputconfirmpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        btn_register = findViewById(R.id.btn_registernow);
        Inputpassword = findViewById(R.id.et_registerpassword);
        Inputphone = findViewById(R.id.et_registerphone);
        Inputconfirmpassword = findViewById(R.id.et_registerconfirmpassword);
        Inputname = findViewById(R.id.et_registername);
        btn_register.setEnabled(false);

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
        Inputconfirmpassword.addTextChangedListener(new TextWatcher() {
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
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkemailandpassword();
            }
        });
    }

    private void checkemailandpassword() {
            if(Inputpassword.getText().toString().equals(Inputconfirmpassword.getText().toString())){
                validatecredentials(Inputname.getText().toString(),Inputphone.getText().toString(),Inputpassword.getText().toString());
            }else {
                Inputconfirmpassword.setError("Passwords don't match");
            }
        }

    private void validatecredentials(String name, final String phone,String password) {
        final DatabaseReference rootref;
        rootref = FirebaseDatabase.getInstance("https://shopar-497ee-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference();
        rootref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(!(snapshot.child("Users").child(phone).exists())){

                    HashMap<String,Object> userdatamap = new HashMap<>();
                    userdatamap.put("phone",phone);
                    userdatamap.put("name",name);
                    userdatamap.put("password",password);

                    rootref.child("Users").child(phone).updateChildren(userdatamap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(registerActivity.this,"ic created successfully",Toast.LENGTH_SHORT).show();
                                        Intent registerintent = new Intent(registerActivity.this,homeActivity.class);
                                        startActivity(registerintent);
                                    }else{
                                        Toast.makeText(registerActivity.this,"Network Error pease try again later!",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                }else{
                    Toast.makeText(registerActivity.this,"this account already exists please login",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(registerActivity.this,error.toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void checkinputs() {
        if (!TextUtils.isEmpty(Inputname.getText())) {
            if (!TextUtils.isEmpty(Inputphone.getText())) {
                if (!TextUtils.isEmpty(Inputpassword.getText())) {
                    if (!TextUtils.isEmpty(Inputconfirmpassword.getText())) {
                        btn_register.setEnabled(true);
                    } else {
                        btn_register.setEnabled(false);
                    }
                } else {
                    btn_register.setEnabled(false);
                }
            } else {
                btn_register.setEnabled(false);
            }
        } else {
            btn_register.setEnabled(false);
        }
    }
}