package com.gahlot.backgroundtask101.basicthreadImplementation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.gahlot.backgroundtask101.R;

public class BasicThreadActivity extends AppCompatActivity implements View.OnClickListener {

    private Handler mHandler;
    private ProgressBar mProgressBar;
    private Button mStartButton;
    MyThread myThread;
    private Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_thread);

        mHandler = new Handler();
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mStartButton = (Button) findViewById(R.id.buttonStart);
        mStartButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonStart:
                myThread = new MyThread(mHandler,mProgressBar);
                thread = new Thread(new MyThread(mHandler,mProgressBar));
                thread.start();
                break;
        }
    }
}
