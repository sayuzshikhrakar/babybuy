package com.example.babybuy;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.*;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.babybuy.Model.ItemInfo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class AddBabyItem extends AppCompatActivity {
    ProgressDialog progressDialog;
    private static final String TAG = "AddBabyItem";
    EditText addItemName,addDescription;
    Button btnaddItem;
    ImageView listItem;

    ItemInfo itemInfo;

    FirebaseAuth mAuth;
    FirebaseUser mUser;

    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference("ItemInformation");

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        addItemName = (EditText) findViewById(R.id.addItem);
        addDescription = (EditText) findViewById(R.id.addDescription);
        btnaddItem = (Button) findViewById(R.id.btnAdd);
        listItem=(ImageView) findViewById(R.id.listItem);

        progressDialog=new ProgressDialog(AddBabyItem.this);

        mAuth= FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();



        btnaddItem.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (!validateItemName()) {

                }else{
                    addItem();
                }
            }
        });

        listItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listAllItem();
            }
        });
    }

    public boolean validateItemName(){
        String value=addItemName.getText().toString();
        if (value.isEmpty()) {
            addItemName.setError("Item Name should not be empty");
            return false;
        } else {
            addItemName.setError(null);
            return true;
        }
    }

    public void addItem(){
        // initializing our variables for each view.
        final String itemName=addItemName.getText().toString();
        final String description = addDescription.getText().toString();
        final String userId= mUser.getUid();

        String uniqueId=null;
        uniqueId= UUID.randomUUID().toString();
        Log.d(TAG, "addItem: "+uniqueId);
        // below line is to get intent as we
        // are getting data via an intent.
        Intent intent = getIntent();

        ItemInfo itemInfo = new ItemInfo(uniqueId,itemName,description,userId);
        ref.child(uniqueId).setValue(itemInfo).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(AddBabyItem.this,"Item Saved", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void listAllItem(){

//        Intent listItem=new Intent(AddBabyItem.this,ItemList.class);
//        startActivity(listItem);
    }
}
