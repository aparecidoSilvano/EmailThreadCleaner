# Email Thread Cleaner

That project exercises the implementation of a LinkedList and the use of Android Services to perform actions in background.

The challenge proposes to implement two applications, the first one should be the only a Service to remove duplicates from a received list, the second one should send to the first a list of emails with some duplicates, the service would handle that list, remove the duplicates and return the result list to the second application.

However, I wasn't able to implement an application with only a service that could be called by other applications. For that reason, I decided to implement the service to clean the email thread on the same application.

[MyLinkedList](app/src/main/java/com/silvanoalbuquerque/emailcleaner/model/mylinkedlist/MyLinkedList.java) is my implementation for the LinkedList, some unit tests can be found on [EmailThreadTest](app/src/test/java/com/silvanoalbuquerque/emailcleaner/EmailThreadTest.java).

![](application.gif)
