package com.example.creditservice.events;

import com.example.creditservice.enums.CreditStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RiskEvaluatedEvent {
    private UUID creditId;
    private UUID userId;
    private int riskScore;
    private CreditStatus status; // APPROVED / REJECTED
}
