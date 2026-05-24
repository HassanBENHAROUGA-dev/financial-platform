package com.example.creditservice.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreditRequestedEvent {
    private UUID creditId;
    private UUID userId;
    private Double amount;
}
