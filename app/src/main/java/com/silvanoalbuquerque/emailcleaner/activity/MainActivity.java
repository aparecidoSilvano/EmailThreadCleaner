package com.silvanoalbuquerque.emailcleaner.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.silvanoalbuquerque.emailcleaner.R;
import com.silvanoalbuquerque.emailcleaner.activity.util.EmailsAdapter;
import com.silvanoalbuquerque.emailcleaner.activity.util.IEmailsListContext;
import com.silvanoalbuquerque.emailcleaner.model.Email;
import com.silvanoalbuquerque.emailcleaner.model.mylinkedlist.MyLinkedList;
import com.silvanoalbuquerque.emailcleaner.service.EmailCleanerJobService;
import com.silvanoalbuquerque.emailcleaner.service.EmailCleanerReceiver;
import com.silvanoalbuquerque.emailcleaner.util.Constants;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements EmailCleanerReceiver.IEventsListener, IEmailsListContext {
    @BindView(R.id.emails_list) RecyclerView emailsRecyclerView;
    @BindView(R.id.pb_loading_emails) ProgressBar pbLoadingEmails;
    @BindView(R.id.tv_count_emails) TextView tvCountEmails;

    private EmailCleanerReceiver cleanerReceiver;
    private MyLinkedList<Email> data;
    private EmailsAdapter emailsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        initUi();
        loadData();
    }

    @Override
    public void onReceiveResult(int resultCode, Bundle resultData) {
        if (resultCode == Constants.JOB_RECEIVER_RESULT_CODE) {
            MyLinkedList<Email> resultList = (MyLinkedList<Email>) resultData.get(Constants.EMAIL_JOB_RESULT_DATA_KEY);
            int countRemovedElements = data.size() - resultList.size();
            data = resultList;
            emailsAdapter.notifyDataSetChanged();

            updateTotalEmailsView();

            String message = getString(R.string.removed_duplicated_emails_message, countRemovedElements);
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public MyLinkedList<Email> getEmails() {
        return data;
    }

    @OnClick(R.id.add_duplicates)
    public void onCLickOnAddDuplicates() {
        generateDuplicates(Constants.COUNT_DUPLICATES);
        String msg = getString(R.string.added_duplicated_emails_message, Constants.COUNT_DUPLICATES);

        updateTotalEmailsView();
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.remove_duplicates)
    public void onCLickOnRemoveDuplicates() {
        Intent mIntent = new Intent(MainActivity.this, EmailCleanerJobService.class);
        mIntent.putExtra(Constants.JOB_RECEIVER_KEY, cleanerReceiver);
        mIntent.putExtra(Constants.EMAILS_LIST_KEY, data);

        EmailCleanerJobService.enqueueWork(MainActivity.this, mIntent);
    }

    private void initUi() {
    }

    private void loadData() {
        cleanerReceiver = new EmailCleanerReceiver(new Handler());
        cleanerReceiver.setReceiver(this);

        data = new MyLinkedList<>();

        Email email1 = new Email("E-mail 1", "user1@gmail.com");
        Email email2 = new Email("E-mail 2", "user2@gmail.com");
        Email email3 = new Email("E-mail 3", "user1@gmail.com");
        Email email4 = new Email("E-mail 4", "user2@gmail.com");
        Email email5 = new Email("E-mail 5", "user1@gmail.com");
        Email email6 = new Email("E-mail 6", "user2@gmail.com");
        Email email7 = new Email("E-mail 7", "user1@gmail.com");
        Email email8 = new Email("E-mail 8", "user2@gmail.com");
        Email email9 = new Email("E-mail 9", "user1@gmail.com");
        Email email10 = new Email("E-mail 10", "user2@gmail.com");

        data.add(email1);
        data.add(email2);
        data.add(email3);
        data.add(email4);
        data.add(email5);
        data.add(email6);
        data.add(email7);
        data.add(email8);
        data.add(email9);
        data.add(email10);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        emailsRecyclerView.setLayoutManager(layoutManager);

        emailsAdapter = new EmailsAdapter(this);
        emailsRecyclerView.setAdapter(emailsAdapter);

        showEmailsList();
        updateTotalEmailsView();
    }

    private void updateTotalEmailsView() {
        String totalStr = getString(R.string.total_of_emails, data.size());
        tvCountEmails.setText(totalStr);
    }

    private void showEmailsList() {
        pbLoadingEmails.setVisibility(View.GONE);
        emailsRecyclerView.setVisibility(View.VISIBLE);
    }

    private void generateDuplicates(int numDuplicates) {
        Random rand = new Random();
        int maxIndex = data.size();

        for (int i = 0; i < numDuplicates; i++) {
            int randomItem = rand.nextInt(maxIndex);

            Email originalEmail = data.get(randomItem);
            Email newEmail = new Email(originalEmail.getSubject(), originalEmail.getRecipient());

            data.add(newEmail);
        }

        emailsAdapter.notifyDataSetChanged();
    }
}