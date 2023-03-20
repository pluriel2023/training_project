package net.pluriel.services;

import net.pluriel.dto.requests.PaymentRequestDto;
import net.pluriel.dto.responses.PaymentResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PaymentService {
    public PaymentResponseDto create(PaymentRequestDto paymentRequestDto);
    public PaymentResponseDto getOne(Integer id);
    public PaymentResponseDto update(PaymentRequestDto paymentRequestDto, Integer id);
    public List<PaymentResponseDto> getAll();
    public Page<PaymentResponseDto> getAllInPage(int page, int size);
    public void delete(Integer paymentId);
}
