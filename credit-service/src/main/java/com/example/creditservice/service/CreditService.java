package com.example.creditservice.service;

import com.example.creditservice.dtos.CreateCreditRequest;
import com.example.creditservice.entity.Credit;
import com.example.creditservice.enums.CreditStatus;
import com.example.creditservice.events.CreditRequestedEvent;
import com.example.creditservice.producer.CreditEventProducer;
import com.example.creditservice.repository.CreditRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreditService {

    private final CreditRepository repository;
    private final CreditEventProducer producer;

    public Credit createCredit(CreateCreditRequest request) {

        Credit credit = Credit.builder()
                .userId(request.getUserId())
                .amount(request.getAmount())
                .status(CreditStatus.PENDING)
                .build();

        Credit saved = repository.save(credit);

        producer.send(
                CreditRequestedEvent.builder()
                        .creditId(saved.getId())
                        .userId(saved.getUserId())
                        .amount(saved.getAmount())
                        .build()
        );

        return saved;
    }
}
