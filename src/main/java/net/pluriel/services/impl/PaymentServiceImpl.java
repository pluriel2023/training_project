package net.pluriel.services.impl;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.pluriel.dto.mappers.PaymentMapper;
import net.pluriel.dto.requests.PaymentRequestDto;
import net.pluriel.dto.responses.PaymentResponseDto;
import net.pluriel.entities.Payment;
import net.pluriel.repositories.PaymentRepository;
import net.pluriel.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service @Slf4j
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentMapper paymentMapper;

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public PaymentResponseDto create(PaymentRequestDto paymentRequestDto) {
        Payment paymentRequest = paymentMapper.convertRequestToEntity(paymentRequestDto);
        paymentRepository.save(paymentRequest);
        return paymentMapper.convertEntityToResponse(paymentRequest);
    }

    @SneakyThrows
    @Override
    public PaymentResponseDto getOne(Integer id) {
        Payment payment = paymentRepository.findById(id).orElseThrow(() -> new Exception("Not Found"));
        return paymentMapper.convertEntityToResponse(payment);
    }

    @SneakyThrows
    @Override
    public PaymentResponseDto update(PaymentRequestDto paymentRequestDto, Integer id) {
        Payment payment = paymentRepository.findById(id).orElseThrow(() -> new Exception("Not Found"));
        Payment paymentRequest = paymentMapper.convertRequestToEntity(paymentRequestDto);
        payment.setMode(paymentRequest.getMode());
        paymentRepository.save(payment);
        return paymentMapper.convertEntityToResponse(payment);
    }

    @Override
    public List<PaymentResponseDto> getAll() {
        List<Payment> all = paymentRepository.findAll();
        List<PaymentResponseDto> result = all.stream().map(paymentMapper::convertEntityToResponse).collect(Collectors.toList());
        return result;
    }

    @Override
    public Page<PaymentResponseDto> getAllInPage(int page, int size) {
        return paymentRepository.findAll(PageRequest.of(page, size)).map(paymentMapper::convertEntityToResponse);
    }

    @Override
    public void delete(Integer paymentId) {

    }
}
