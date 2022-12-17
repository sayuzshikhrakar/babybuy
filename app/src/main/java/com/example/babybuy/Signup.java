package com.example.babybuy;
import android.util.Patterns;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import com.example.babybuy.Model.User;
import com.example.babybuy.Model.UserInfo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.renderscript.Sampler;
import android.util.Log;
import android.view.*;
import android.widget.*;

import java.util.HashMap;

public class Signup extends AppCompatActivity {
    public static final String TAG = "Signup";
    ProgressDialog progressDialog;
    EditText inputFirstName, inputLastName,inputPhone,inputEmail,inputPassword,inputConfirmpassword, inputUserName;
    Button  btnSingup;
    String emailPattern="[a-zA-Z0-9._-]+@[a-z]+\\\\.+[a-z]+";
    String phonePatter="^\\+[0-9]{10,10}$";
    String namePatter="^[A-Za-z]+$";

    User userInfo;

    FirebaseAuth mAuth;
    FirebaseUser mUser;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference("UserInformation");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onClick: "+"we are here");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        userInfo = new User();



        inputFirstName=findViewById(R.id.crtFirstName);
        inputLastName=findViewById(R.id.crtLastName);
        inputPhone=findViewById(R.id.crtPhone);
        inputEmail=findViewById(R.id.crtEmail);
        inputPassword=findViewById(R.id.crtpassword);
        inputConfirmpassword=findViewById(R.id.crtconfirmpassword);
        inputUserName=findViewById(R.id.crtUserName);
        btnSingup=findViewById(R.id.btnSingup);

        progressDialog=new ProgressDialog(Signup.this);

        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();


        btnSingup.setOnClickListener(new View.OnClickListener(){
            private static final String TAG = "Signup";
            @Override
            public void onClick(View v){

                PerformAuth();
            }
        });

    }

    private void PerformAuth() {
        final String firstName=inputFirstName.getText().toString();
        final String lastName=inputLastName.getText().toString();
        final String Phone=inputPhone.getText().toString();
        final String Email=inputEmail.getText().toString();
        final String Password=inputPassword.getText().toString();
        final String confirmPassword=inputConfirmpassword.getText().toString();
        final String userName=inputUserName.getText().toString();

        if(!firstName.matches(namePatter)){
            inputFirstName.setError("Name should not contain numeric or symbol characters");
        }else if(!lastName.matches(namePatter)){
            inputLastName.setError("Name should not contain numeric or symbol characters");
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(Email).matches()){
            inputEmail.setError("Not an Appropriate Email Format");
        }else if(Password.isEmpty()|| Password.length()<6){
            inputPassword.setError("Password must be more than 6 characters");
        }else if(!Password.matches(confirmPassword)){
            inputConfirmpassword.setError("Password doesn't match");
        }else{
            progressDialog.setMessage("Account is being created.Please wait...");
            progressDialog.setTitle("Sing up");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();



            mAuth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        progressDialog.dismiss();
                        sendUserToNextActivity();
                        Toast.makeText(Signup.this,"Signup",Toast.LENGTH_SHORT).show();
                        UserInfo userinfo = new UserInfo(firstName,lastName,Phone,Email,Password,userName);
                        ref.child(userName).setValue(userinfo).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(Signup.this,"User Created Successfully", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }else{
                        progressDialog.dismiss();
                        Toast.makeText(Signup.this,""+task.getException(),Toast.LENGTH_SHORT).show();
                    }
                }
            });





        }


    }
    private void sendUserToNextActivity() {
        Intent intent=new Intent(Signup.this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }




}
