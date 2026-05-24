package com.example.creditservice.entity;

import com.example.creditservice.enums.CreditStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "credits")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Credit {

    @Id
    @GeneratedValue
    private UUID id;

    private UUID userId;

    private Double amount;

    private CreditStatus status; // PENDING / APPROVED / REJECTED

    private Integer riskScore;
}
