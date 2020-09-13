package com.silvanoalbuquerque.emailcleaner;

import com.silvanoalbuquerque.emailcleaner.model.Email;
import com.silvanoalbuquerque.emailcleaner.model.mylinkedlist.MyLinkedList;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class EmailThreadTest {
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

    @Test
    public void testInsertionsAtTheEnd() {
        MyLinkedList<Email> myLinkedList = new MyLinkedList<>();
        myLinkedList.add(email1);

        assertEquals(1, myLinkedList.size());

        myLinkedList.add(email2);
        myLinkedList.add(email3);
        myLinkedList.add(email4);

        assertEquals(4, myLinkedList.size());

        myLinkedList.add(email5);
        myLinkedList.add(email6);
        myLinkedList.add(email7);
        myLinkedList.add(email8);
        myLinkedList.add(email9);
        myLinkedList.add(email10);

        assertEquals(10, myLinkedList.size());

        assertEquals(myLinkedList.get(0), email1);
        assertEquals(myLinkedList.get(1), email2);
        assertEquals(myLinkedList.get(2), email3);
        assertEquals(myLinkedList.get(3), email4);
        assertEquals(myLinkedList.get(4), email5);
        assertEquals(myLinkedList.get(5), email6);
        assertEquals(myLinkedList.get(6), email7);
        assertEquals(myLinkedList.get(7), email8);
        assertEquals(myLinkedList.get(8), email9);
        assertEquals(myLinkedList.get(9), email10);

        try {
            myLinkedList.get(10);
            fail();
        } catch (IndexOutOfBoundsException e) {
            // OK
        }

        myLinkedList.remove(0);
        assertEquals(9, myLinkedList.size());
        assertEquals(myLinkedList.get(0), email2);

        myLinkedList.remove(8);
        assertEquals(8, myLinkedList.size());
        assertEquals(myLinkedList.get(7), email9);

        for (int i = 7; i >= 0; i--) {
            myLinkedList.remove(i);
            assertEquals(i, myLinkedList.size());
        }

        assertEquals(0, myLinkedList.size());
    }
}
