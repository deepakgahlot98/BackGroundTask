package com.gahlot.backgroundtask101;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.gahlot.backgroundtask101.ThreadPoolExecutor.ThreadPoolActivity;
import com.gahlot.backgroundtask101.basicthreadImplementation.BasicThreadActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button basicThreadButton, threadpoolButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        threadpoolButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ThreadPoolActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
    }

    private void init() {
        basicThreadButton = findViewById(R.id.basicThread);
        threadpoolButton = findViewById(R.id.threadpoolbutton);
    }

    @Override
    public void onClick(View view) {
        if (basicThreadButton.equals(view)) {
            Intent intent = new Intent(this, BasicThreadActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        }
    }
}
