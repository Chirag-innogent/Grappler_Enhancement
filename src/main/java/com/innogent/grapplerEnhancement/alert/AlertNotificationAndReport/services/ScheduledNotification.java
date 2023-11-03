package com.innogent.grapplerEnhancement.alert.AlertNotificationAndReport.services;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ScheduledNotification {
    @Scheduled(cron = "0 * * * * *")
    public void scheduledMethod(){
        System.out.println(new Date().toString());
    }

    @Scheduled(cron = "0 0 11 * * MON-FRI")
    public void dailyMethod(){
        System.out.println("daily update brother : "+new Date().toString());
    }
}


