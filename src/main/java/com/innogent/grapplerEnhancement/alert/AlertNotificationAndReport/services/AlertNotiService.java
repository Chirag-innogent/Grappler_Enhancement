package com.innogent.grapplerEnhancement.alert.AlertNotificationAndReport.services;

import com.innogent.grapplerEnhancement.alert.AlertNotificationAndReport.entities.Rule;
import com.innogent.grapplerEnhancement.alert.AlertNotificationAndReport.payloads.AlertDto;
import com.innogent.grapplerEnhancement.alert.AlertNotificationAndReport.payloads.NotificationDtoForCreate;
import com.innogent.grapplerEnhancement.alert.AlertNotificationAndReport.payloads.ProjectDto;
import com.innogent.grapplerEnhancement.alert.AlertNotificationAndReport.payloads.TicketDto;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlertNotiService {

    @Autowired
    private  static Logger logger= LoggerFactory.getLogger(AlertNotiService.class);

    @Autowired
    NotificationService notificationService;

    @Autowired
    AlertSevice alertSevice;

    @Autowired
    ModelMapper modelMapper;
    public void createAlertNoti(TicketDto ticketDto, ProjectDto projectDto, Rule rule){
        if (rule.getAction().equalsIgnoreCase("Notification")) {

            String[] channel = rule.getChannel().split(",");
            logger.info(rule.getChannel());
            logger.info("working on split");
            for (String s : channel) {

                if (s.equalsIgnoreCase("In_app")) {
                    logger.info("try to add notificaton");
                    NotificationDtoForCreate notiDto = new NotificationDtoForCreate(ticketDto, projectDto,rule);
                    notificationService.createNotification(notiDto);
                    logger.info("created noti");
                }
            }
        }

        if (rule.getAction().equalsIgnoreCase("Alert")) {

            String[] channel = rule.getChannel().split(",");
            for (String s : channel) {
                if (s.equalsIgnoreCase("In_app")) {

                    AlertDto alertDto = new AlertDto(ticketDto,projectDto, rule);
                    alertSevice.createAlert(alertDto);
                }
            }
        }
    }
}
