package com.silvanoalbuquerque.emailcleaner.activity.util;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.silvanoalbuquerque.emailcleaner.R;
import com.silvanoalbuquerque.emailcleaner.model.Email;

public class EmailsAdapter extends RecyclerView.Adapter<EmailVH> {
    private IEmailsListContext ctx;

    public EmailsAdapter(IEmailsListContext ctx) {
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public EmailVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_email, parent, false);

        return new EmailVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmailVH holder, int position) {
        Email email = ctx.getEmails().get(position);
        holder.bind(email);
    }

    @Override
    public int getItemCount() {
        return ctx.getEmails().size();
    }
}
