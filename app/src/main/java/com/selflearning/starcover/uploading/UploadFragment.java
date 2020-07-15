package com.selflearning.starcover.uploading;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.selflearning.starcover.R;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;

import io.grpc.Context;


public class UploadFragment extends Fragment {
    //media player added
    MediaPlayer mediaPlayer;
    SeekBar seekBar;
    Chronometer runnTime, totalTime;
    TextView songName, artistName;
    FloatingActionButton endBtn, againBtn, uploadBtn;
    //play icon
    ImageView imageView;
    //button to stop playing added
    Button btnStop;

    public UploadFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_upload, container, false);

        seekBar = root.findViewById(R.id.upload_cover_progress);
        runnTime = root.findViewById(R.id.run_time_cover);
        totalTime = root.findViewById(R.id.upload_total_time_cover);
        songName = root.findViewById(R.id.upload_cover_name);
        artistName = root.findViewById(R.id.upload_cover_artist);
     // declaring and setting oon clicklistener
        imageView=root.findViewById(R.id.upload_player_btn);
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        //stopbutton to stop play
        btnStop=root.findViewById(R.id.btnStop);
         btnStop.setOnClickListener(new View.OnClickListener() {
             @Override
            public void onClick(View view) {
            mediaPlayer.stop();
            getActivity().finish();
             }
           });//ended

        songName.setText(getArguments().getString("SONG"));
        artistName.setText(getArguments().getString("ARTIST"));
        totalTime.setBase(getArguments().getLong("DURATION"));
        RecordFragment r=new RecordFragment();
        String recordPath=getActivity().getExternalFilesDir("/").getAbsolutePath();
        File f = new File(recordPath);
        String[] pathnames = f.list();
        final String file= recordPath + "/" + pathnames[pathnames.length - 1];
        //play image listener
        // Comment for muzammil bsdka

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    FileDescriptor fd = new FileInputStream(file).getFD();
                    mediaPlayer.setDataSource(fd);
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                       } catch (IOException e) {
                    e.printStackTrace();
                }

                }
        });//ended listener

        endBtn = root.findViewById(R.id.end_recording_btn);
        endBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        getActivity().finish();
                    }
                });

        againBtn = root.findViewById(R.id.again_recording_btn);
        againBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle arguments = new Bundle();
                arguments.putString("SONG",songName.getText().toString());
                arguments.putString("ARTIST",artistName.getText().toString());
                RecordFragment fragment = new RecordFragment();
                fragment.setArguments(arguments);
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager()
                        .beginTransaction();
                fragmentTransaction.replace(R.id.recording_fragment,fragment).commit();

            }
        });


        uploadBtn = root.findViewById(R.id.upload_recording_btn);
        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });

        return root;
    }


}