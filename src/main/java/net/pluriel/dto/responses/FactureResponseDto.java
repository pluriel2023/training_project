package net.pluriel.dto.responses;


import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class FactureResponseDto {
	private Integer id;
	private Date datePaymentFacture;
	private List<OrderResponseDto> orders;
}
