package com.selflearning.starcover.uploading;

import android.Manifest;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.selflearning.starcover.R;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import io.gresse.hugo.vumeterlibrary.VuMeterView;

public class RecordFragment extends Fragment {

    long timeWhenStopped = 0;
    boolean isRecording = false;

    Chronometer chronometer;
    VuMeterView meterView;
    SeekBar seekBar;
    FloatingActionButton recordingBtn;
    TextView songName, aartistName;
    Button finishBtn;
    String recordPermission= Manifest.permission.RECORD_AUDIO;
    int PERMISSION_CODE=21;
    MediaRecorder mediaRecorder;
    MediaPlayer mp;

    public RecordFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.fragment_record, container, false);

        seekBar = (SeekBar) root.findViewById(R.id.instrumental_progress);
        chronometer = (Chronometer) root.findViewById(R.id.cover_recording_time);
        meterView = (VuMeterView) root.findViewById(R.id.meter);
        recordingBtn = (FloatingActionButton) root.findViewById(R.id.recording_button);
        recordingBtn.setImageResource(R.drawable.ic_mic_dark);
        songName = (TextView) root.findViewById(R.id.recording_instrumental_name);
        aartistName = (TextView) root.findViewById(R.id.recording_instrumental_artist);
        finishBtn = (Button) root.findViewById(R.id.finish_btn);

        String song = getArguments().getString("SONG");
        String artist = getArguments().getString("ARTIST");

        songName.setText(song);
        aartistName.setText(artist);

        meterView.pause();


        recordingBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                if (!isRecording) {
                    if (checkPermissions()){

                        if (timeWhenStopped == 0) {
                            startChronometer();
                        } else {
                            resumeChronometer();
                        }
                        meterView.resume(true);
                        isRecording = !isRecording;
                        recordingBtn.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimaryDark)));
                        recordingBtn.setImageResource(R.drawable.ic_mic_light);
                        startRecording();


                }
                }else {
                    pauseChronometer();
                    meterView.pause();
                    isRecording = !isRecording;
                    recordingBtn.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorAccent)));
                    recordingBtn.setImageResource(R.drawable.ic_mic_dark);
                    //stop recording
                    stopSong();
                    stopRecording();
                }
            }

            private void stopRecording() {
                mediaRecorder.stop();
                mediaRecorder.release();
                mediaRecorder=null;

            }

            public void startRecording() {
                String recordPath=getActivity().getExternalFilesDir("/").getAbsolutePath();
                SimpleDateFormat formatter=new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss", Locale.UK);
                Date now= new Date();
                String recordFile ="Recording "+ formatter.format(now)+ ".3gp";

                mediaRecorder=new MediaRecorder();
                mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                //this is the path to save the song externally, make changes to upload it as well.
                mediaRecorder.setOutputFile(recordPath+ "/" + recordFile);
                mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                mp=new MediaPlayer();

                try {
                    mediaRecorder.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mediaRecorder.start();
                playSong();
            }

            private boolean checkPermissions() {
                if(ActivityCompat.checkSelfPermission(getContext(), recordPermission)== PackageManager.PERMISSION_GRANTED ){
                    return true;
                }
                ActivityCompat.requestPermissions(getActivity(),new String[]{recordPermission},PERMISSION_CODE);
                return false;
            }
        });

        finishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle arguments = new Bundle();
                arguments.putString("SONG",songName.getText().toString());
                arguments.putString("ARTIST",aartistName.getText().toString());
                arguments.putLong("DURATION",chronometer.getBase());

                UploadFragment fragment = new UploadFragment();
                fragment.setArguments(arguments);


                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager()
                        .beginTransaction();
                fragmentTransaction.replace(R.id.recording_fragment,fragment).commit();
            }
        });

        return root;
    }

    public void startChronometer(){
        chronometer.setBase(SystemClock.elapsedRealtime());
        timeWhenStopped = 0;
        chronometer.start();
    }
    public void resumeChronometer() {
        chronometer.setBase(SystemClock.elapsedRealtime() - timeWhenStopped);
        chronometer.start();
    }
    public void pauseChronometer() {
        chronometer.stop();
        timeWhenStopped = SystemClock.elapsedRealtime() - chronometer.getBase();
    }
    public void playSong() {

        String url = getArguments().getString("url");

        try {
            //here is only one song url, insert code for puttong url of selected song
        mp.setDataSource(url);
        mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.start();
            }
        });

        mp.prepare();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void stopSong(){
        mp.stop();
    }

}