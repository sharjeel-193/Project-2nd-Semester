package com.selflearning.starcover;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firestore.v1beta1.DocumentRemove;
import com.selflearning.starcover.ui.login.LoginActivity;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class RegistrationActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    FirebaseFirestore firestore;
    EditText userNameF,userIdF,emailF,passwordF,confirmPasswordF,dateOfBirthF;
    RadioButton male,female;
    RadioGroup genderGroup;
    Button signUpBtn;
    DatePickerDialog.OnDateSetListener dateListener;
    String databaseUserID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        getSupportActionBar().hide();

        firebaseAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        findingViews();

        findViewById(R.id.log_in_link).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        dateOfBirthSetter();
        signingUpProcedure();

    }

    public void findingViews(){
        userNameF = (EditText) findViewById(R.id.name_register);
        userIdF = (EditText) findViewById(R.id.register_userId);
        emailF = (EditText) findViewById(R.id.email_register);
        passwordF = (EditText) findViewById(R.id.password_register);
        confirmPasswordF = (EditText) findViewById(R.id.password_confirm);
        male = (RadioButton) findViewById(R.id.male_radio);
        female = (RadioButton) findViewById(R.id.female_radio);
        signUpBtn = (Button) findViewById(R.id.register_button);
        dateOfBirthF = (EditText) findViewById(R.id.date_register);
        genderGroup = (RadioGroup) findViewById(R.id.genders);
    }

    public void dateOfBirthSetter(){

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        dateOfBirthF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(RegistrationActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month +1;
                        String date = day+"/"+month+"/"+year;
                        dateOfBirthF.setText(date);
                    }
                },year,month,day);
                dialog.show();
            }
        });
    }
    public void signingUpProcedure(){
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(emailF.getText())){
                    emailF.setError("Please Enter Email Id");
                    emailF.requestFocus();
                    return;
                }
                if (passwordF.getText().toString().isEmpty()) {
                    passwordF.setError("Please Type Password");
                    passwordF.requestFocus();
                    return;
                }
                if (!passwordF.getText().toString().equals(confirmPasswordF.getText().toString())) {
                    confirmPasswordF.setError("Sorry your password dont match");
                    confirmPasswordF.requestFocus();
                    return;
                }

                final String email = emailF.getText().toString();
                final String userId = userIdF.getText().toString();
                final String userName = userNameF.getText().toString();
                String password = passwordF.getText().toString();
                final String DOB = dateOfBirthF.getText().toString();
                final String gender = male.isChecked()?"Male":"Female";

                firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(RegistrationActivity.this,"User Registered",Toast.LENGTH_SHORT).show();
                            databaseUserID = firebaseAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = firestore.collection("USERS").document(databaseUserID);
                            Map<String,Object> user = new HashMap<>();
                            user.put("Full Name",userName);
                            user.put("Email",email);
                            user.put("User ID",userId);
                            user.put("Gender",gender);
                            user.put("Date of Birth",DOB);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(getApplicationContext(),"User data added",Toast.LENGTH_LONG);
                                }
                            });
                            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                            finish();
                        } else {
                            Toast.makeText(RegistrationActivity.this,"Error: "+task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });
    }
    public void errorDialogs() {
        if (emailF.getText().toString().isEmpty()) {
            emailF.setError("Please Enter Email Id");
            emailF.requestFocus();
        }
        if (userNameF.getText().toString().isEmpty()) {
            userNameF.setError("Please Enter User Name");
            userNameF.requestFocus();
        }
        if (userIdF.getText().toString().isEmpty()) {
            userIdF.setError("Please Choose your userID");
            userIdF.requestFocus();
        }
        if (passwordF.getText().toString().isEmpty()) {
            passwordF.setError("Please Type Password");
            passwordF.requestFocus();
        }
        if (!passwordF.getText().toString().equals(confirmPasswordF.getText().toString())) {
            confirmPasswordF.setError("Sorry your password dont match");
            confirmPasswordF.requestFocus();
        }
    }
}