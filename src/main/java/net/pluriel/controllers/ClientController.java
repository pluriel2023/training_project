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

import net.pluriel.dto.requests.ClientRequestDto;
import net.pluriel.dto.responses.ClientResponseDto;
import net.pluriel.services.ClientService;

@RestController
@RequestMapping("/clients")
public class ClientController {

	@Autowired
	private ClientService clientService;
	
	@PostMapping
	public ResponseEntity<ClientResponseDto> create(@RequestBody ClientRequestDto clientRequestDto){
		return new ResponseEntity<ClientResponseDto>(clientService.create(clientRequestDto), HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ClientResponseDto> getOne(@PathVariable(name = "id") Integer clientId){
		return new ResponseEntity<ClientResponseDto>(clientService.getOne(clientId), HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ClientResponseDto> update(@PathVariable(name = "id") Integer clientId, @RequestBody ClientRequestDto clientRequestDto){
		return new ResponseEntity<ClientResponseDto>(clientService.update(clientRequestDto, clientId), HttpStatus.OK);
	}
	@GetMapping("/all")
	public ResponseEntity<List<ClientResponseDto>> getAll(){
		return new ResponseEntity<List<ClientResponseDto>>(clientService.getAll(), HttpStatus.OK);
	}
	
	@GetMapping("/all_paginate")
	public ResponseEntity<Page<ClientResponseDto>> getAllPaginate(
			@RequestParam(name = "page") int page,
			@RequestParam(name = "size") int size
		){
		return new ResponseEntity<Page<ClientResponseDto>>(clientService.getAllInPage(page, size), HttpStatus.OK);
	}
	@DeleteMapping("/delete")
	public ResponseEntity<?> delete(@RequestParam(name = "id") Integer clientId){
	 clientService.delete(clientId);
	 return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	
}
