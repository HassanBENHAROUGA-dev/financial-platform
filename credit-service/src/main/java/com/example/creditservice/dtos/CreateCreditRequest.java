package com.example.creditservice.dtos;

import lombok.Data;

import java.util.UUID;

@Data
public class CreateCreditRequest {
    private UUID userId;
    private Double amount;
}
