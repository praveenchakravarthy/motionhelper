package com.example.registrationapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    protected TextView tv_eg_reg_num;

    protected EditText edt_reg_no;
    protected Button btn_check_num;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

    }

    public void init(){
      edt_reg_no = findViewById(R.id.edt_reg_no);
        tv_eg_reg_num = findViewById(R.id.tv_eg_reg_num);
        btn_check_num = findViewById(R.id.btn_check_num);

    }

}