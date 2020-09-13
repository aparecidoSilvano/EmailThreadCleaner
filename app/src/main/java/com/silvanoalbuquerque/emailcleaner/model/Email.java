package com.silvanoalbuquerque.emailcleaner.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.Serializable;

public class Email implements Serializable {

    private static final long serialVersionUID = -7610423066355963472L;

    private String subject;
    private String recipient;

    public Email(String subject, String recipient) {
        this.subject = subject;
        this.recipient = recipient;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        final Email otherEmail = (Email) obj;

        if ((this.subject == null) ? (otherEmail.subject != null) : !this.subject.equals(otherEmail.subject)) {
            return false;
        }

        return (this.recipient == null) ? (otherEmail.recipient == null) : this.recipient.equals(otherEmail.recipient);
    }

    @NonNull
    @Override
    public String toString() {
        return "Email{" +
                "subject='" + subject + '\'' +
                '}';
    }
}
