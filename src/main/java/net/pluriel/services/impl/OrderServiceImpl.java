package net.pluriel.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.pluriel.dto.mappers.OrderMapper;
import net.pluriel.dto.requests.OrderRequestDto;
import net.pluriel.dto.responses.OrderResponseDto;
import net.pluriel.entities.Client;
import net.pluriel.entities.Order;
import net.pluriel.entities.Product;
import net.pluriel.repositories.ClientRepository;
import net.pluriel.repositories.OrderRepository;
import net.pluriel.repositories.ProductRepository;
import net.pluriel.services.OrderService;

@Service @Slf4j
public class OrderServiceImpl  implements OrderService{
	
	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private ProductRepository productRepository;
	
	@SneakyThrows
	@Override
	public OrderResponseDto create(OrderRequestDto OrderRequestDto) {
		Order orderRequest = orderMapper.convertRequestToEntity(OrderRequestDto);
		Client client=clientRepository.findById(OrderRequestDto.getClientId()).orElseThrow(() -> new Exception("Client Not Found"));
		Product product=productRepository.findById(OrderRequestDto.getProductId()).orElseThrow(() -> new Exception("Product Not Found"));
		orderRequest.setClient(client);
		orderRequest.setProduct(product);
		orderRepository.save(orderRequest);

		return orderMapper.convertEntityToResponse(orderRequest);
	}

	@SneakyThrows
	@Override
	public OrderResponseDto getOne(Integer id) {
		// TODO Auto-generated method stub
		Order orderRequest =orderRepository.findById(id).orElseThrow(() -> new Exception("Order Not Found"));
		return orderMapper.convertEntityToResponse(orderRequest);
	}
	@SneakyThrows
	@Override
	public OrderResponseDto update(OrderRequestDto OrderRequestDto, Integer id) {
		// TODO Auto-generated method stub
		Order orderRequest = orderRepository.findById(id).orElseThrow(() -> new Exception("Order Not Found"));
		Client client=clientRepository.findById(OrderRequestDto.getClientId()).orElseThrow(() -> new Exception("Client Not Found"));
		Product product=productRepository.findById(OrderRequestDto.getProductId()).orElseThrow(() -> new Exception("Product Not Found"));
		orderRequest.setClient(client);
		orderRequest.setProduct(product);
		orderRequest.setQuantity(OrderRequestDto.getQuantity());
		orderRequest.setDateOrder(OrderRequestDto.getDateOrder());
		orderRepository.save(orderRequest);

		return orderMapper.convertEntityToResponse(orderRequest);
	}

	@Override
	public List<OrderResponseDto> getAll() {
		// TODO Auto-generated method stub
		List<Order > all=orderRepository.findAll();
		List<OrderResponseDto> result =all.stream().map(orderMapper::convertEntityToResponse).collect(Collectors.toList());
		return result;
	}

	@Override
	public Page<OrderResponseDto> getAllInPage(int page, int size) {
		// TODO Auto-generated method stub
		
		return orderRepository.findAll(PageRequest.of(page, size)).map(orderMapper::convertEntityToResponse);
	}

	@SneakyThrows
	@Override
	public void delete(Integer OrderId) {
		// TODO Auto-generated method stub
		Order order=orderRepository.findById(OrderId).orElseThrow(() -> new Exception("Order Not Found"));
		orderRepository.delete(order);
	}

}
