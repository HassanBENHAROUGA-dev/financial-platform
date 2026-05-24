package com.example.creditservice.producer;

import com.example.creditservice.events.CreditRequestedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreditEventProducer {

    private final KafkaTemplate<String, CreditRequestedEvent> kafkaTemplate;

    private static final String TOPIC = "credit-requested";

    public void send(CreditRequestedEvent event) {
        kafkaTemplate.send(TOPIC, event);
    }
}
