package com.selflearning.starcover.uploading;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.selflearning.starcover.R;

public class UploadingActivity extends AppCompatActivity {



    TextView toolbarId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploading);

        getSupportActionBar().hide();

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        toolbarId = (TextView) findViewById(R.id.toolbar_secondary_id);
        DocumentReference documentReference = firestore.collection("USERS").document(firebaseAuth.getCurrentUser().getUid());
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                toolbarId.setText(documentSnapshot.getString("User ID"));
            }
        });

        Bundle extras = getIntent().getExtras();
        String song = extras.getString("SONG");
        String artist = extras.getString("ARTIST");
        String url = extras.getString("url");
//
        Fragment recordFrag = new RecordFragment();

        Bundle data = new Bundle();
        data.putString("SONG",song);
        data.putString("ARTIST",artist);
        data.putString("url", url);
        recordFrag.setArguments(data);

        getSupportFragmentManager().beginTransaction().replace(R.id.recording_fragment,recordFrag).commit();

        ImageView backBtn = (ImageView) findViewById(R.id.toolbar_back_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}