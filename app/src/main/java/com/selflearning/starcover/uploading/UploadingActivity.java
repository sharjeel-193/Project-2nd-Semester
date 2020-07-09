package com.selflearning.starcover.uploading;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.selflearning.starcover.R;

public class UploadingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploading);

        getSupportActionBar().hide();

        Bundle extras = getIntent().getExtras();
        String song = extras.getString("SONG");
        String artist = extras.getString("ARTIST");
//
        Fragment recordFrag = new RecordFragment();

        Bundle data = new Bundle();
        data.putString("SONG",song);
        data.putString("ARTIST",artist);
        recordFrag.setArguments(data);

        getSupportFragmentManager().beginTransaction().replace(R.id.recording_fragment,recordFrag).commit();

        ImageView backBtn = (ImageView) findViewById(R.id.recording_back_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}