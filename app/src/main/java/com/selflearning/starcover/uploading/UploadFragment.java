package com.selflearning.starcover.uploading;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.selflearning.starcover.R;


public class UploadFragment extends Fragment {

    SeekBar seekBar;
    Chronometer runnTime, totalTime;
    TextView songName, artistName;
    FloatingActionButton endBtn, againBtn, uploadBtn;

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

        songName.setText(getArguments().getString("SONG"));
        artistName.setText(getArguments().getString("ARTIST"));
        totalTime.setBase(getArguments().getLong("DURATION"));

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