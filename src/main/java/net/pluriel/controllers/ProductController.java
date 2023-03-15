package net.pluriel.controllers;

import net.pluriel.dto.requests.ProductRequestDto;
import net.pluriel.dto.responses.ProductResponseDto;
import net.pluriel.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponseDto> create(@RequestBody ProductRequestDto productRequestDto){
        return new ResponseEntity<ProductResponseDto>(productService.create(productRequestDto), HttpStatus.CREATED);
    }

    @PutMapping("/editProduct/{id}")
    public ResponseEntity<ProductResponseDto> update(@PathVariable(name = "id") Integer productId, @RequestBody ProductRequestDto productRequestDto){
        return new ResponseEntity<ProductResponseDto>(productService.update(productRequestDto, productId), HttpStatus.OK);
    }

    @GetMapping("/allProduct")
    public ResponseEntity<List<ProductResponseDto>> getAllProducts(){
        return new ResponseEntity<List<ProductResponseDto>>(productService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getProduct(@PathVariable(name = "id") Integer idProduct){
        return new ResponseEntity<ProductResponseDto>(productService.getOne(idProduct), HttpStatus.OK);
    }
//
//    @GetMapping("/all_paginate")
//    public ResponseEntity<Page<ClientResponseDto>> getAllPaginate(
//            @RequestParam(name = "page") int page,
//            @RequestParam(name = "size") int size
//    ){
//        return new ResponseEntity<Page<ClientResponseDto>>(clientService.getAllInPage(page, size), HttpStatus.OK);
//    }

    @GetMapping("/allPages")
    public ResponseEntity<Page<ProductResponseDto>> getAllPaginate(
            @RequestParam(name = "page") int page,
            @RequestParam(name = "size") int size
    ){
        return  new ResponseEntity<Page<ProductResponseDto>>(productService.getAllInPage(page, size), HttpStatus.OK);
    }

}
