package com.icbt.jksalessystem.util.notification;

import com.icbt.jksalessystem.util.JwtUtil;

import static com.icbt.jksalessystem.util.notification.NotificationFactory.NotificationType.EMAIL;
import static com.icbt.jksalessystem.util.notification.NotificationFactory.NotificationType.SMS;

/**
 * Created by IntelliJ IDEA.
 * User: sadeesha
 * Date: 2021-02-27
 */
public class DesignPatternDemo {

    public static void main(String[] args) {


        // Without using factory
        // Tightly coupled code. Resolved at compile time.
        EmailNotificationSender emailSender1 = EmailNotificationSender.getInstance();
        SMSNotificationSender smsSender1 = SMSNotificationSender.getInstance();

        // Using factory
        // Loosely coupled code. Resolved at runtime based on the requested type.
        NotificationSender emailSender2 = NotificationFactory.getInstance().getNotificationSender(EMAIL);
        NotificationSender smsSender2 = NotificationFactory.getInstance().getNotificationSender(SMS);

    }
}
