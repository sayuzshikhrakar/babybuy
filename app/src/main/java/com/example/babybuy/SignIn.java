package com.example.babybuy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import com.google.firebase.database.Query;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import android.util.Log;
import android.view.*;
import android.widget.*;

public class SignIn extends AppCompatActivity {
    private static final String TAG = "SignIn";
    EditText edtUserName, edtPassword;
    Button btnSignIn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        edtUserName = (EditText) findViewById(R.id.edtUserName);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        btnSignIn = (Button) findViewById(R.id.signup);




        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateuserName() | !validatepassWord()) {

                }else{
                    checkUser();
                }
                }
        });
    }

    public Boolean validateuserName(){
        String value=edtUserName.getText().toString();
        if (value.isEmpty()) {
            edtUserName.setError("Username cannot be empty");
            return false;
        } else {
            edtUserName.setError(null);
            return true;
        }
    }

    public Boolean validatepassWord(){
        String value = edtPassword.getText().toString();
        if (value.isEmpty()) {
            edtPassword.setError("Password cannot be empty");
            return false;
        } else {
            edtPassword.setError(null);
            return true;
        }
    }

    public void checkUser(){
        final String userUsername = edtUserName.getText().toString().trim();
        final String userPassword = edtPassword.getText().toString().trim();

        //Initiate firebase
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("UserInformation");
        Query checkUserDatabase = reference.orderByChild("userName").equalTo(userUsername);

        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {
                Log.d(TAG, "onDataChange: "+snapshot);
                if (snapshot.exists()){

                    edtUserName.setError(null);
                    String passwordFromDB = snapshot.child(userUsername).child("password").getValue(String.class);

                    if (passwordFromDB.equals(userPassword)) {
                        edtUserName.setError(null);
                        Toast.makeText(SignIn.this,"Login Successfull", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SignIn.this, AddBabyItem.class);

                        startActivity(intent);

//

                    } else {
                        edtPassword.setError("Invalid Credentials");
                        edtPassword.requestFocus();
                    }
                } else {
                    edtUserName.setError("User does not exist");
                    edtUserName.requestFocus();
                }

                Log.d(TAG, "onDataChange: "+"222222222222222222222222222222222222222222222222222222222222");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}



