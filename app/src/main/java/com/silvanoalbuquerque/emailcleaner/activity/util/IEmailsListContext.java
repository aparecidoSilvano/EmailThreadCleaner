package com.silvanoalbuquerque.emailcleaner.activity.util;

import com.silvanoalbuquerque.emailcleaner.model.Email;
import com.silvanoalbuquerque.emailcleaner.model.mylinkedlist.MyLinkedList;

public interface IEmailsListContext {
    MyLinkedList<Email> getEmails();
}
