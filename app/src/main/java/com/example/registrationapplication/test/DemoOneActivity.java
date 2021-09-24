package com.example.registrationapplication.test;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.registrationapplication.R;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class DemoOneActivity extends AppCompatActivity {

    protected Button btn_start;
    protected TextView time_tv;
    Intent mServiceIntent;
    private BroadCastService broadCastService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_one);

        initializeVariables();
        init();
    }

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
              //  Log.i ("Service status", "Running");
                return true;
            }
        }
     //   Log.i ("Service status", "Not running");
        return false;
    }

    private void initializeVariables(){

        btn_start = findViewById(R.id.btn_start_timer);
        time_tv = findViewById(R.id.tv_timer);


    }

    public void init(){

        btn_start.setOnClickListener(v -> {
            Intent i = new Intent(this, BroadCastService.class);
            startService(i);

            broadCastService = new BroadCastService();
            mServiceIntent = new Intent(this, broadCastService.getClass());
            if (!isMyServiceRunning(broadCastService.getClass())) {
                startService(mServiceIntent);
            }

        });



    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

          //  System.out.println(intent);

            updateGUI(intent);


        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(broadcastReceiver, new IntentFilter(BroadCastService.COUNTDOWN_BR));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(broadcastReceiver);
    }

    @Override
    protected void onStop() {

        try {
            unregisterReceiver(broadcastReceiver);

        } catch (Exception ae){
            ae.printStackTrace();
        }
        super.onStop();

    }

    @Override
    protected void onDestroy() {

//        if(isMyServiceRunning(broadCastService.getClass())){
//            stopService(new Intent(this,BroadCastService.class));
//        } else {
//            startService(new Intent(this,BroadCastService.class));
//        }



        Intent broadcastIntent = new Intent();
        broadcastIntent.setAction("restartService");
        broadcastIntent.setClass(this, Restarter.class);
        this.sendBroadcast(broadcastIntent);
        super.onDestroy();

    }



    private void updateGUI(Intent i){
         if(i.getExtras() != null){
             long millisUntilFinished = i.getLongExtra("countDown", 0);

             String timer = String.format(Locale.getDefault(), "%02d:%02d",
                         0, millisUntilFinished / 1000);

                time_tv.setText(timer);

         }
    }


}