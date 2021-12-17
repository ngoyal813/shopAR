package com.example.shopar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class adminActivity extends AppCompatActivity {

    private String categoryname,description,price,pname,savecurentdate,savecurrenttime;
    private Button selectimage,selectmodel,addproduct;
    private EditText productname,productdescription,productprice;
    private static final int gallerypick = 1,modelpick =2;
    private Uri imageuri,modeluri;
    private String productrandomkey,downloadimageurl,downloadmodelurl;
    private StorageReference productimagesref,modelref;
    private DatabaseReference productref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        categoryname = getIntent().getExtras().get("category").toString();
        selectimage = findViewById(R.id.btn_adminselectimage);
        selectmodel = findViewById(R.id.btn_adminselectmodel);
        productname = findViewById(R.id.et_adminproductname);
        productdescription = findViewById(R.id.et_adminproductdescription);
        productprice = findViewById(R.id.et_adminproductprice);
        addproduct = findViewById(R.id.btn_adminaddproduct);

        productimagesref = FirebaseStorage.getInstance().getReference().child("Product Images");
        modelref = FirebaseStorage.getInstance().getReference().child("Product models");
        productref = FirebaseDatabase.getInstance("https://shopar-497ee-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("Products");

        selectimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opengallery();
            }
        });
        selectmodel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openfiles();
            }
        });
        addproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateproductdata();
            }
        });
    }

    private void openfiles() {
        Intent fileintent = new Intent();
        fileintent.setAction(Intent.ACTION_PICK);
        startActivityForResult(fileintent,modelpick);
    }
    private void opengallery() {
        Intent galleryIntent = new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent,gallerypick);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == modelpick && resultCode == RESULT_OK && data!=null){
            modeluri = data.getData();
            imageuri = data.getData();
        }
    }

    private void validateproductdata() {

        description = productdescription.getText().toString();
        price = productprice.getText().toString();
        pname = productname.getText().toString();

        if(imageuri ==null){
            Toast.makeText(adminActivity.this,"Product image is necessary",Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(description)){
            Toast.makeText(adminActivity.this,"Please write product description",Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(pname)){
            Toast.makeText(adminActivity.this,"Please write product name",Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(price)){
            Toast.makeText(adminActivity.this,"Please mention the price of the product",Toast.LENGTH_SHORT).show();
        }else{
            storeproductinformation();
        }

    }

    private void storeproductinformation() {
        Calendar  calendar = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd,yyyy");
        savecurentdate = currentDate.format(calendar.getTime());

        SimpleDateFormat currenttime = new SimpleDateFormat("HH:mm:ss a");
        savecurrenttime = currenttime.format(calendar.getTime());

        productrandomkey = savecurentdate+savecurrenttime;

        StorageReference imagepath = productimagesref.child(imageuri.getLastPathSegment() + productrandomkey + ".jpg");
        StorageReference modelpath = modelref.child(modeluri.getLastPathSegment() + productrandomkey + ".glb");

        final UploadTask uploadTask = imagepath.putFile(imageuri);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                String message = e.toString();
                Toast.makeText(adminActivity.this,"Error : " + message ,Toast.LENGTH_SHORT).show();

            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(adminActivity.this,"Product image uploaded successfully",Toast.LENGTH_SHORT).show();
                Task<Uri> uriTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful()){
                            throw task.getException();
                        }
                        downloadimageurl = imagepath.getDownloadUrl().toString();
                        return imagepath.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        downloadimageurl = task.getResult().toString();
                        Toast.makeText(adminActivity.this,"got image successfully",Toast.LENGTH_SHORT).show();
                        saveproductinfotodatabse();
                    }
                });
            }
        });

        final UploadTask task = modelpath.putFile(modeluri);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                String message = e.toString();
                Toast.makeText(adminActivity.this,"Error : " + message ,Toast.LENGTH_SHORT).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(adminActivity.this,"Product model uploaded successfully",Toast.LENGTH_LONG).show();
                Task<Uri> uritask = task.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if(!task.isSuccessful()){
                            throw task.getException();
                        }
                        downloadmodelurl = modelpath.getDownloadUrl().toString();
                        return modelpath.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        downloadmodelurl = task.getResult().toString();
                        Toast.makeText(adminActivity.this,"got model successfully",Toast.LENGTH_SHORT).show();
                        saveproductinfotodatabse();
                    }
                });
            }
        });

    }

    private void saveproductinfotodatabse() {
        HashMap<String,Object> productmap = new HashMap<>();
        productmap.put("pid",productrandomkey);
        productmap.put("date",savecurentdate);
        productmap.put("time",savecurrenttime);
        productmap.put("description",description);
        productmap.put("image",downloadimageurl);
        productmap.put("model",downloadmodelurl);
        productmap.put("category",categoryname);
        productmap.put("price",price);
        productmap.put("name",pname);

        productref.child(productrandomkey).updateChildren(productmap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Intent intent = new Intent(adminActivity.this,admincategoryactivity.class);
                            startActivity(intent);
                            Toast.makeText(adminActivity.this,"product added successfully",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            String message = task.getException().toString();
                            Toast.makeText(adminActivity.this,message,Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }


}