package com.example.notificationservice.listener;

import com.example.notificationservice.enums.CreditStatus;
import com.example.notificationservice.event.RiskEvaluatedEvent;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationListener {

    @PostConstruct
    public void init() {
        log.info("🚀 NotificationListener started");
    }

    @KafkaListener(topics = "risk-evaluated", groupId = "notification-group")
    public void consume(RiskEvaluatedEvent event) {
        log.info("📩 Notification received: {}", event);

        if (CreditStatus.APPROVED.equals(event.getStatus())) {
            log.info("✅ Credit APPROVED notification sent");
        } else {
            log.info("❌ Credit REJECTED notification sent");
        }
    }
}
