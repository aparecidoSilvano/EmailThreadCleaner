package com.silvanoalbuquerque.emailcleaner.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.silvanoalbuquerque.emailcleaner.service.EmailCleanerJobService;
import com.silvanoalbuquerque.emailcleaner.service.EmailCleanerReceiver;
import com.silvanoalbuquerque.emailcleaner.R;

public class MainActivity extends AppCompatActivity implements EmailCleanerReceiver.IEventsListener {

    private EmailCleanerReceiver cleanerReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cleanerReceiver = new EmailCleanerReceiver(new Handler());
        cleanerReceiver.setReceiver(this);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(MainActivity.this, EmailCleanerJobService.class);
                mIntent.putExtra("maxCountValue", 10);
                mIntent.putExtra("receiver", cleanerReceiver);
                EmailCleanerJobService.enqueueWork(MainActivity.this, mIntent);
            }
        });
    }

    @Override
    public void onReceiveResult(int resultCode, Bundle resultData) {
        Toast.makeText(this, "SERVICE HAS FINISHED IT JOB, " + resultData.getString("data"), Toast.LENGTH_LONG).show();
    }
}