package com.example.registrationapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
import com.google.android.play.core.tasks.OnCompleteListener;
import com.google.android.play.core.tasks.Task;

public class RatingActivity extends AppCompatActivity {

    protected ReviewManager reviewManager;

    protected ReviewInfo reviewInfo = null;

    protected Button btn_rate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        init();
        startReviewFlow();
    }


    public void init(){

        btn_rate = findViewById(R.id.btn_rate);

        reviewManager = ReviewManagerFactory.create(getApplicationContext());

        Task<ReviewInfo> manager = reviewManager.requestReviewFlow();
        manager.addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                reviewInfo = task.getResult();
            } else {
            }
        });


        btn_rate.setOnClickListener(V-> {
            startReviewFlow();
        });

    }

    private void startReviewFlow() {

        if (reviewInfo != null) {
            Task<Void> flow = reviewManager.launchReviewFlow(this, reviewInfo);
            flow.addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(Task<Void> task) {
                    Toast.makeText(getApplicationContext(), "In App Rating complete", Toast.LENGTH_LONG).show();
                }
            });
        }
        else {
        }
    }



}