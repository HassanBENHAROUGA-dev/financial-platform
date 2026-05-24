package com.example.riskservice.listener;

import com.example.riskservice.enums.CreditStatus;
import com.example.riskservice.event.RiskEvaluatedEvent;
import com.example.riskservice.event.UserRegisteredEvent;
import com.example.riskservice.producer.RiskEventProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserRegisteredListener {

    private final RiskEventProducer riskEventProducer;

    //@KafkaListener(topics = "user-registered", groupId = "risk-service-group")
    @KafkaListener(topics = "credit-requested")
    public void consume(UserRegisteredEvent event) {

        log.info("🔥 Received user event: {}", event);

        //int riskScore = calculateRisk(event.getEmail());
        int riskScore = 99;
        CreditStatus status = riskScore > 70 ? CreditStatus.REJECTED : CreditStatus.APPROVED;

        log.info("🧠 Risk score = {}", riskScore);

        riskEventProducer.send(
                RiskEvaluatedEvent.builder()
                        .creditId(event.getCreditId()) // 🔥 IMPORTANT
                        .userId(event.getUserId())
                        .riskScore(riskScore)
                        .status(status)
                        .build()
        );
    }
    private int calculateRisk(String email) {
        return Math.abs(email.hashCode() % 100);
    }
}
