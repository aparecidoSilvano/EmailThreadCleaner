package com.silvanoalbuquerque.emailcleaner.service;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;

import com.silvanoalbuquerque.emailcleaner.model.Email;
import com.silvanoalbuquerque.emailcleaner.model.mylinkedlist.MyLinkedList;
import com.silvanoalbuquerque.emailcleaner.util.Constants;

public class EmailCleanerJobService extends JobIntentService {

    private static final int JOB_ID = 2;

    public static void enqueueWork(Context context, Intent intent) {
        enqueueWork(context, EmailCleanerJobService.class, JOB_ID, intent);
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        MyLinkedList<Email> emailsList = (MyLinkedList<Email>) intent.getExtras().get(Constants.EMAILS_LIST_KEY);
        emailsList.removeDuplicates();

        ResultReceiver resultReceiver = intent.getParcelableExtra(Constants.JOB_RECEIVER_KEY);

        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.EMAIL_JOB_RESULT_DATA_KEY, emailsList);
        resultReceiver.send(Constants.JOB_RECEIVER_RESULT_CODE, bundle);
    }
}
