package com.example.riskservice.event;

import com.example.riskservice.enums.CreditStatus;
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
    private UUID userId;
    private String email;
    private UUID creditId;
    private int riskScore;
    private CreditStatus status;
}
