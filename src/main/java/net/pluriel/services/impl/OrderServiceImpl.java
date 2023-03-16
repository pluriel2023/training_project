package net.pluriel.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import net.pluriel.dto.mappers.OrderMapper;
import net.pluriel.dto.responses.OrderResponseDto;
import net.pluriel.repositories.OrderRepository;

import net.pluriel.services.OrderService;

@Service @Slf4j
public class OrderServiceImpl  implements OrderService{
	
	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private OrderRepository orderRepository;


	@Override
	public Page<OrderResponseDto> getAllInPage(int page, int size) {
		return orderRepository.findAll(PageRequest.of(page, size)).map(orderMapper::convertEntityToResponse);
	}

	@Override
	public Page<OrderResponseDto> getAllByClient(Integer clientId, int page, int size) {
		Page<OrderResponseDto> list = orderRepository.getAllByClient(clientId, PageRequest.of(page, size)).map(orderMapper::convertEntityToResponse);
		return list;
	}

}
