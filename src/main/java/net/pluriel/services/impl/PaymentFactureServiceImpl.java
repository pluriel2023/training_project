package net.pluriel.services.impl;
import net.pluriel.dto.mappers.PaymentFactureMapper;

import net.pluriel.dto.responses.PaymentFactureResponseDto;
import net.pluriel.repositories.PaymentFactureRepository;
import net.pluriel.services.PaymentFactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class PaymentFactureServiceImpl implements PaymentFactureService {


    @Autowired
    private PaymentFactureMapper paymentFactureMapper;
    @Autowired
    private PaymentFactureRepository paymentFactureRepository;

    @Override
    public Page<PaymentFactureResponseDto> getAllInPage(int page, int size) {
        return paymentFactureRepository.findAll(PageRequest.of(page,size)).map(paymentFactureMapper::convertEntityToResponse);
    }
}
