package com.selflearning.starcover;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.selflearning.starcover.ui.login.LoginActivity;

public class SettingsActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    FirebaseFirestore firestore;

    TextView toolbarId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getSupportActionBar().hide();

        firebaseAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        toolbarId = (TextView) findViewById(R.id.toolbar_secondary_id);
        DocumentReference documentReference = firestore.collection("USERS").document(firebaseAuth.getCurrentUser().getUid());
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                toolbarId.setText(documentSnapshot.getString("User ID"));
            }
        });

        //GOING BACK

        findViewById(R.id.toolbar_back_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //LOGGING OUT

        findViewById(R.id.log_out_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(getApplicationContext(), LoginActivity.class);        // Specify any activity here e.g. home or splash or login etc
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("EXIT", true);
                startActivity(i);
                finish();

            }
        });

        //DELETING ACCOUNT

        findViewById(R.id.delete_account).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUser currentUser = firebaseAuth.getCurrentUser();
                final String autoId = currentUser.getUid();

                currentUser.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            deleteUserData(autoId);
                            Intent i = new Intent(getApplicationContext(), LoginActivity.class);        // Specify any activity here e.g. home or splash or login etc
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            i.putExtra("EXIT", true);
                            Toast.makeText(getApplicationContext(),"Deleted Account Successfully",Toast.LENGTH_SHORT);
                            startActivity(i);
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(),"Error in deletting account",Toast.LENGTH_SHORT);
                        }
                    }
                });

            }
        });

    }


    public void deleteUserData(String id){
        firestore.collection("USERS").document(id).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"User data also delted",Toast.LENGTH_LONG);
                } else {
                    Toast.makeText(getApplicationContext(),"Error in data deleteion",Toast.LENGTH_LONG);
                }
            }
        });
        StorageReference mStorageRef = FirebaseStorage.getInstance().getReference().child("profile_images/" + id + ".jpg");
        mStorageRef.delete();
    }

}