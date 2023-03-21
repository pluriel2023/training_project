package net.pluriel.services;

import net.pluriel.dto.responses.PaymentFactureResponseDto;
import org.springframework.data.domain.Page;

public interface PaymentFactureService {
	 public Page<PaymentFactureResponseDto> getAllInPage(int page, int size);
	}
