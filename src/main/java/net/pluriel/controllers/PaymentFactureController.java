package net.pluriel.controllers;

import net.pluriel.dto.responses.OrderResponseDto;
import net.pluriel.dto.responses.PaymentFactureResponseDto;
import net.pluriel.entities.PaymentFacture;
import net.pluriel.services.OrderService;
import net.pluriel.services.PaymentFactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/PaymentFacture")
public class PaymentFactureController {

    @Autowired
    private PaymentFactureService paymentFactureService;

    @GetMapping("/all_paginate")
    public ResponseEntity<Page<PaymentFactureResponseDto>> getAllPaginate(
            @RequestParam(name = "page") int page,
            @RequestParam(name = "size") int size
    ){
        return new ResponseEntity<Page<PaymentFactureResponseDto>>(paymentFactureService.getAllInPage(page, size), HttpStatus.OK);
    }

}
