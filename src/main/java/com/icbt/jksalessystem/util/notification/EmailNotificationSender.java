package com.icbt.jksalessystem.util.notification;

import org.hibernate.cfg.NotYetImplementedException;

/**
 * Created by IntelliJ IDEA.
 * User: sadeesha
 * Date: 2021-02-27
 */
public class EmailNotificationSender implements NotificationSender {

    private static EmailNotificationSender INSTANCE;

    private EmailNotificationSender() {
    }

    public static EmailNotificationSender getInstance() {
        if (INSTANCE == null) INSTANCE = new EmailNotificationSender();
        return INSTANCE;
    }

    @Override
    public void sendNotification(String message, String receiver) {
        throw new NotYetImplementedException();
    }
}
