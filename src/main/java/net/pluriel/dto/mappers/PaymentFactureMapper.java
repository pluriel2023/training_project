package net.pluriel.dto.mappers;


import net.pluriel.dto.requests.PaymentFactureRequestDto;

import net.pluriel.dto.responses.PaymentFactureResponseDto;
import net.pluriel.entities.PaymentFacture;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PaymentFactureMapper {
	 PaymentFactureMapper INSTANCE = Mappers.getMapper( PaymentFactureMapper.class );

	    PaymentFacture convertRequestToEntity(PaymentFactureRequestDto paymentFactureRequest);

	    PaymentFacture convertResponseToEntity(PaymentFactureResponseDto paymentFactureResponse);

	    PaymentFactureResponseDto convertEntityToResponse(PaymentFacture PaymentFacture);
	}