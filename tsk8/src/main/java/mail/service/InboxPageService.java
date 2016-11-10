package mail.service;

import mail.page.InboxPage;

public class InboxPageService {
    private static String INBOX = "Входящие";
    private InboxPage inboxPage = new InboxPage();


    public boolean isCorrectPage() {
        String title = inboxPage.getInboxPageTitle();
        return title.contains(INBOX);
    }

    public boolean isMailCome() {
       return inboxPage.isMailCame();
    }
}
