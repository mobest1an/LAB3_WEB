package com.mobest1an.labs.MBeans;

import com.mobest1an.labs.services.Result;

import javax.management.AttributeChangeNotification;
import javax.management.MBeanNotificationInfo;
import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;
import java.util.List;

public class UserStatisticProvider extends NotificationBroadcasterSupport implements UserStatisticProviderMBean {

    private int userPointsCount;
    private boolean userDoubleMissed;
    private long sequenceNumber = 1;

    private List<Result> results;

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    private void calculateUserPointsCount() {
        userPointsCount = results.size();
    }

    public void calculateUserDoubleMissed() {
        boolean oldUserDoubleMissed = userDoubleMissed;
        userDoubleMissed = results.get(results.size() - 1).getHit().equals("Нет")
                && results.get(results.size() - 2).getHit().equals("Нет");

        if (userDoubleMissed) {
            Notification notification = new AttributeChangeNotification(
                    this,
                    sequenceNumber++,
                    System.currentTimeMillis(),
                    "The user made 2 \"misses\" in a row",
                    "Double miss",
                    "boolean",
                    oldUserDoubleMissed,
                    userDoubleMissed
            );

            sendNotification(notification);
        }
    }

    @Override
    public MBeanNotificationInfo[] getNotificationInfo() {
        String[] types = new String[]{
                AttributeChangeNotification.ATTRIBUTE_CHANGE
        };

        String name = AttributeChangeNotification.class.getName();
        String description = "An attribute of this MBean has changed";
        MBeanNotificationInfo info = new MBeanNotificationInfo(types, name, description);
        return new MBeanNotificationInfo[]{info};
    }

    @Override
    public int getUserPointsCount() {
        calculateUserPointsCount();
        return userPointsCount;
    }
}
