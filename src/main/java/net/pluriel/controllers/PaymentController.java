package net.pluriel.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.pluriel.dto.requests.PaymentRequestDto;
import net.pluriel.dto.responses.PaymentResponseDto;
import net.pluriel.services.PaymentService;

@RestController
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	private PaymentService PaymentService;
	
	@PostMapping
	public ResponseEntity<PaymentResponseDto> create(@RequestBody PaymentRequestDto PaymentRequestDto){
		return new ResponseEntity<PaymentResponseDto>(PaymentService.create(PaymentRequestDto), HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PaymentResponseDto> getOne(@PathVariable(name = "id") Integer PaymentId){
		return new ResponseEntity<PaymentResponseDto>(PaymentService.getOne(PaymentId), HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<PaymentResponseDto> update(@PathVariable(name = "id") Integer PaymentId, @RequestBody PaymentRequestDto PaymentRequestDto){
		return new ResponseEntity<PaymentResponseDto>(PaymentService.update(PaymentRequestDto, PaymentId), HttpStatus.OK);
	}
	@GetMapping("/all")
	public ResponseEntity<List<PaymentResponseDto>> getAll(){
		return new ResponseEntity<List<PaymentResponseDto>>(PaymentService.getAll(), HttpStatus.OK);
	}
	
	@GetMapping("/all_paginate")
	public ResponseEntity<Page<PaymentResponseDto>> getAllPaginate(
			@RequestParam(name = "page") int page,
			@RequestParam(name = "size") int size
		){
		return new ResponseEntity<Page<PaymentResponseDto>>(PaymentService.getAllInPage(page, size), HttpStatus.OK);
	}
	@DeleteMapping("/delete")
	public ResponseEntity<?> delete(@RequestParam(name = "id") Integer PaymentId){
	 PaymentService.delete(PaymentId);
	 return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	
}
