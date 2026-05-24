package com.example.creditservice.listener;

import com.example.creditservice.entity.Credit;
import com.example.creditservice.events.RiskEvaluatedEvent;
import com.example.creditservice.repository.CreditRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RiskEventListener {

    private final CreditRepository repository;

    @KafkaListener(topics = "risk-evaluated", groupId = "credit-service-group")
    public void consume(RiskEvaluatedEvent event) {

        log.info("📥 Received risk event: {}", event);

        Credit credit = repository.findById(event.getCreditId())
                .orElseThrow(() -> new RuntimeException("Credit not found: " + event.getCreditId()));

        credit.setRiskScore(event.getRiskScore());
        credit.setStatus(event.getStatus());

        repository.save(credit);

        log.info("✅ Credit updated: {}", credit);
    }
}
