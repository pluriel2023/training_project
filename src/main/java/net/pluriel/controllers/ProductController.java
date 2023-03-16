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

import com.fasterxml.jackson.databind.introspect.TypeResolutionContext.Empty;

import net.pluriel.dto.requests.ClientRequestDto;
import net.pluriel.dto.requests.ProductRequestDto;
import net.pluriel.dto.responses.ClientResponseDto;
import net.pluriel.dto.responses.ProductResponseDto;
import net.pluriel.services.ProductService;



@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@PostMapping
	public ResponseEntity<ProductResponseDto> create(@RequestBody ProductRequestDto productRequestDto){
		return new ResponseEntity<ProductResponseDto>(productService.create(productRequestDto), HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProductResponseDto> getOne(@PathVariable(name = "id") Integer productId){
		return new ResponseEntity<ProductResponseDto>(productService.getOne(productId), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ProductResponseDto> update(@PathVariable(name = "id") Integer productId,@RequestBody ProductRequestDto productRequestDto){
		return new ResponseEntity<ProductResponseDto>(productService.update(productRequestDto,productId), HttpStatus.OK);
	}
	@GetMapping("/all")
	public ResponseEntity<List<ProductResponseDto>> getAll(){
		return new ResponseEntity<List<ProductResponseDto>>(productService.getAll(), HttpStatus.OK);
	}
	

	@GetMapping("/all_paginate")
	public ResponseEntity<Page<ProductResponseDto>> getAllPaginate(
			@RequestParam(name = "page") int page,
			@RequestParam(name = "size") int size
			){
		return new ResponseEntity<Page<ProductResponseDto>>(productService.getAllInPage(page, size), HttpStatus.OK);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<?> delete(@RequestParam(name = "id") Integer productId){
	 productService.delete(productId);
	 return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
