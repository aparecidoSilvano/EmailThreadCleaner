package com.silvanoalbuquerque.emailcleaner.activity.util;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.silvanoalbuquerque.emailcleaner.R;
import com.silvanoalbuquerque.emailcleaner.model.Email;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EmailVH extends RecyclerView.ViewHolder {

    @BindView(R.id.item_email_subject) TextView tvEmailSubject;
    @BindView(R.id.item_email_recipient) TextView tvEmailRecipient;

    public EmailVH(@NonNull View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);
    }

    public void bind(Email email) {
        tvEmailSubject.setText(email.getSubject());
        tvEmailRecipient.setText(email.getRecipient());
    }
}
