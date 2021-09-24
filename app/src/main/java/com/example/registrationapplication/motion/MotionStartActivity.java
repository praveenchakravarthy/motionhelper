package com.example.registrationapplication.motion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;

import com.example.registrationapplication.MainActivity;
import com.example.registrationapplication.R;

public class MotionStartActivity extends AppCompatActivity {

    protected View motion_btn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motion_start);

        initVariables();
    }

    private void initVariables() {

        motion_btn = findViewById(R.id.button);

        motion_btn.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                startActivity(new Intent(MotionStartActivity.this,MotionEndActivity.class));
                return false;
            }
        });


    }
}