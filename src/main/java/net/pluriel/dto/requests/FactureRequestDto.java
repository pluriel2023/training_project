package net.pluriel.dto.requests;

import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FactureRequestDto {
	private Date datePaymentFacture;
	private List<OrderRequestDto> orders;
}
