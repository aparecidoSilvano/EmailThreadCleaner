package com.silvanoalbuquerque.emailcleaner.activity;

import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.silvanoalbuquerque.emailcleaner.R;
import com.silvanoalbuquerque.emailcleaner.activity.util.EmailsAdapter;
import com.silvanoalbuquerque.emailcleaner.activity.util.IEmailsListContext;
import com.silvanoalbuquerque.emailcleaner.model.Email;
import com.silvanoalbuquerque.emailcleaner.model.mylinkedlist.MyLinkedList;
import com.silvanoalbuquerque.emailcleaner.service.EmailCleanerReceiver;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements EmailCleanerReceiver.IEventsListener, IEmailsListContext {
    @BindView(R.id.emails_list)
    RecyclerView emailsRecyclerView;

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

        /*Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(MainActivity.this, EmailCleanerJobService.class);
                mIntent.putExtra("maxCountValue", 10);
                mIntent.putExtra("receiver", cleanerReceiver);
                EmailCleanerJobService.enqueueWork(MainActivity.this, mIntent);
            }
        });*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public void onReceiveResult(int resultCode, Bundle resultData) {
        Toast.makeText(this, "SERVICE HAS FINISHED IT JOB, " + resultData.getString("data"), Toast.LENGTH_LONG).show();
    }

    @Override
    public MyLinkedList<Email> getEmails() {
        return data;
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
    }
}