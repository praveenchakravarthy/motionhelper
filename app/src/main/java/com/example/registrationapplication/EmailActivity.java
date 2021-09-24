package com.example.registrationapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EmailActivity extends AppCompatActivity {

   protected EditText edt_text;
   protected Button btn_send_mail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);

        init();
    }

    public void init(){

      edt_text = (EditText) findViewById(R.id.edt_txt);
      btn_send_mail = (Button) findViewById(R.id.btn_send_mail);

      btn_send_mail.setOnClickListener(V-> {
          callSendMail();
      });

    }

    private void callSendMail() {

        String message = edt_text.getText().toString();

        Intent i = new Intent(Intent.ACTION_SEND);
        i.putExtra(Intent.EXTRA_EMAIL, new String[]{"praveen.chakravarthy@readyassist.in"});
        i.putExtra(Intent.EXTRA_SUBJECT, "Testing");
        i.putExtra(Intent.EXTRA_TEXT, message);
        i.setClassName("com.google.android.gm",
                "com.google.android.gm.ComposeActivityGmail");
        i.setType("message/rfc822");
     //  startActivity(Intent.createChooser(i, getString(R.string.label_support_feedback_choose)));


        }


    }
