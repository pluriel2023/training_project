package net.pluriel.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.pluriel.dto.mappers.PaymentMapper;
import net.pluriel.dto.requests.PaymentRequestDto;
import net.pluriel.dto.responses.PaymentResponseDto;
import net.pluriel.entities.Payment;
import net.pluriel.repositories.PaymentRepository;
import net.pluriel.services.PaymentService;

@Service @Slf4j
public class PaymentSrviceImpl  implements PaymentService{
	
	@Autowired
	private PaymentMapper PaymentMapper;
	
	@Autowired
	private PaymentRepository PaymentRepository;
	
	@Override
	public PaymentResponseDto create(PaymentRequestDto PaymentRequestDto) {
		Payment PaymentRequest = PaymentMapper.convertRequestToEntity(PaymentRequestDto);
		PaymentRepository.save(PaymentRequest);
		return PaymentMapper.convertEntityToResponse(PaymentRequest);
	}

	@SneakyThrows
	@Override
	public PaymentResponseDto getOne(Integer id){
		Payment Payment = PaymentRepository.findById(id).orElseThrow(() -> new Exception("Not Found"));

		return PaymentMapper.convertEntityToResponse(Payment);
	}

	@SneakyThrows
	@Override
	public PaymentResponseDto update(PaymentRequestDto PaymentRequestDto, Integer id) {
		Payment Payment = PaymentRepository.findById(id).orElseThrow(() -> new Exception("Not Found"));
		Payment PaymentRequest = PaymentMapper.convertRequestToEntity(PaymentRequestDto);
		
		Payment.setMode(PaymentRequest.getMode());
		
		
		PaymentRepository.save(Payment);
		
		return PaymentMapper.convertEntityToResponse(Payment);
	}

	@Override
	public List<PaymentResponseDto> getAll() {
		List<Payment> all = PaymentRepository.findAll();
		List<PaymentResponseDto> result = all.stream().map(PaymentMapper::convertEntityToResponse).collect(Collectors.toList());
		return result;
	}

	@Override
	public Page<PaymentResponseDto> getAllInPage(int page, int size) {
		return PaymentRepository.findAll(PageRequest.of(page, size)).map(PaymentMapper::convertEntityToResponse);
	}
	@SneakyThrows
	@Override
	public void delete(Integer PaymentId) {
		// TODO Auto-generated method stub
		Payment Payment = PaymentRepository.findById(PaymentId).orElseThrow(() -> new Exception("Not Found"));
		PaymentRepository.delete(Payment);
		
	}
	

}
