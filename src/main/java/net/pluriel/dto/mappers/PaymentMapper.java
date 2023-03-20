package net.pluriel.dto.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import net.pluriel.dto.requests.PaymentRequestDto;
import net.pluriel.dto.responses.PaymentResponseDto;
import net.pluriel.entities.Payment;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

PaymentMapper INSTANCE = Mappers.getMapper( PaymentMapper.class );
	
	Payment convertRequestToEntity(PaymentRequestDto paymentRequest);
	
	Payment convertResponseToEntity(PaymentResponseDto paymentResponse);
	
	PaymentResponseDto convertEntityToResponse(Payment payment);
}
