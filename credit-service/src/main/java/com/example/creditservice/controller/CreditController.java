package com.example.creditservice.controller;

import com.example.creditservice.dtos.CreateCreditRequest;
import com.example.creditservice.entity.Credit;
import com.example.creditservice.service.CreditService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/credits")
@RequiredArgsConstructor
public class CreditController {

    private final CreditService service;

    @PostMapping
    public Credit create(@RequestBody CreateCreditRequest request) {
        return service.createCredit(request);
    }
}
