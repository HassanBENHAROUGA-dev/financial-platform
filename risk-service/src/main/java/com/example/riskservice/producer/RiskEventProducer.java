package com.example.riskservice.producer;

import com.example.riskservice.event.RiskEvaluatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RiskEventProducer {

    private final KafkaTemplate<String, RiskEvaluatedEvent> kafkaTemplate;

    private static final String TOPIC = "risk-evaluated";

    public void send(RiskEvaluatedEvent event) {
        kafkaTemplate.send(TOPIC, event);
    }
}
