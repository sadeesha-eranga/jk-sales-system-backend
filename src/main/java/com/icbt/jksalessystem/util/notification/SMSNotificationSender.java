package com.icbt.jksalessystem.util.notification;

import org.hibernate.cfg.NotYetImplementedException;

/**
 * Created by IntelliJ IDEA.
 * User: sadeesha
 * Date: 2021-02-27
 */
public class SMSNotificationSender implements NotificationSender {

    private static SMSNotificationSender INSTANCE;

    private SMSNotificationSender() {
    }

    public static SMSNotificationSender getInstance() {
        if (INSTANCE == null) INSTANCE = new SMSNotificationSender();
        return INSTANCE;
    }

    @Override
    public void sendNotification(String message, String receiver) {
        throw new NotYetImplementedException();
    }
}
