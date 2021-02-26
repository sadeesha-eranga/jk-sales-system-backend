package com.icbt.jksalessystem.util.notification;

/**
 * Created by IntelliJ IDEA.
 * User: sadeesha
 * Date: 2021-02-26
 */
public class NotificationFactory {

    public enum NotificationType {
        SMS, EMAIL
    }

    private static NotificationFactory notificationFactory;

    private NotificationFactory() {
    }

    public static NotificationFactory getInstance() {
        if (notificationFactory == null) notificationFactory = new NotificationFactory();
        return notificationFactory;
    }

    public NotificationSender getNotificationSender(NotificationType type) {
        switch (type) {
            case SMS: return SMSNotificationSender.getInstance();

            case EMAIL: return EmailNotificationSender.getInstance();

            default: throw new IllegalArgumentException();
        }
    }
}
