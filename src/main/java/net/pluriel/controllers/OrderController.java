package net.pluriel.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.pluriel.dto.requests.OrderRequestDto;
import net.pluriel.dto.responses.ClientResponseDto;
import net.pluriel.dto.responses.OrderResponseDto;
import net.pluriel.dto.responses.OrderResponseDto;
import net.pluriel.services.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@PostMapping
	public ResponseEntity<OrderResponseDto> create(@RequestBody OrderRequestDto orderRequestDto){
		return new ResponseEntity<OrderResponseDto>(orderService.create(orderRequestDto), HttpStatus.CREATED);
	}
	@GetMapping("/{id}")
	public ResponseEntity<OrderResponseDto> getOne(@PathVariable(name = "id") Integer orderId){
		return new ResponseEntity<OrderResponseDto>(orderService.getOne(orderId), HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<OrderResponseDto>> getAll(){
		return new ResponseEntity<List<OrderResponseDto>>(orderService.getAll(), HttpStatus.OK);
	}
}
