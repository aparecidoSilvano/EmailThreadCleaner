package com.silvanoalbuquerque.emailcleaner.service;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;

public class EmailCleanerJobService extends JobIntentService {

    /**
     * Unique job ID for this service.
     */
    private static final int JOB_ID = 2;

    final Handler mHandler = new Handler();

    public static void enqueueWork(Context context, Intent intent) {
        enqueueWork(context, EmailCleanerJobService.class, JOB_ID, intent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        showToast("Job Execution Started");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //showToast("Job Execution Finished");
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        /*
          Write code here.. Perform Long operation here such as Download/Upload of
          file, Sync Some data
          The system or framework is already holding a wake lock for us at this point
         */

        int maxCount = intent.getIntExtra("maxCountValue", -1);
        /*
         * Suppose we want to print 1 to 1000 number with one-second interval, Each task will take time 1 sec, So here now sleeping thread for one second.
         */
        for (int i = 0; i < maxCount; i++) {
            Log.d("TESTE", "onHandleWork: The number is: " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        ResultReceiver resultReceiver = intent.getParcelableExtra("receiver");
        Bundle bundle = new Bundle();
        bundle.putString("data", "AEEEEEEEEEEEEE");
        resultReceiver.send(123, bundle);
    }

    // Helper for showing tests
    void showToast(final CharSequence text) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(EmailCleanerJobService.this, text, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
