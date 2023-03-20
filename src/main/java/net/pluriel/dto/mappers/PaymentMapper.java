package net.pluriel.dto.mappers;

import net.pluriel.dto.requests.PaymentRequestDto;
import net.pluriel.dto.responses.PaymentResponseDto;
import net.pluriel.entities.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
    PaymentMapper INSTANCE = Mappers.getMapper( PaymentMapper.class );

    Payment convertRequestToEntity(PaymentRequestDto paymentRequest);

    Payment convertResponseToEntity(PaymentResponseDto paymentResponse);

    PaymentResponseDto convertEntityToResponse(Payment payment);
}
