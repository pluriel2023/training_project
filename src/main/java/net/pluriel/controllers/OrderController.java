package net.pluriel.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.pluriel.dto.responses.OrderResponseDto;
import net.pluriel.services.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@GetMapping("/all_paginate")
	public ResponseEntity<Page<OrderResponseDto>> getAllPaginate(
			@RequestParam(name = "page") int page,
			@RequestParam(name = "size") int size
		){
		return new ResponseEntity<Page<OrderResponseDto>>(orderService.getAllInPage(page, size), HttpStatus.OK);
	}
	
	@GetMapping("/all/{clientId}")
	public ResponseEntity<Page<OrderResponseDto>> getAllByClient(
			@PathVariable(name = "clientId") Integer clientId,
			@RequestParam(name = "page") int page,
			@RequestParam(name = "size") int size
			){
		return new ResponseEntity<Page<OrderResponseDto>>(orderService.getAllByClient(clientId, page, size), HttpStatus.OK);
	}

}
