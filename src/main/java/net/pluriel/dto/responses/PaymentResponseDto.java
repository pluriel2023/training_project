package net.pluriel.dto.responses;


import lombok.Data;
import lombok.NoArgsConstructor;
import net.pluriel.entities.Mode;

@Data
@NoArgsConstructor
public class PaymentResponseDto {

	private Integer id;
	private Mode mode;
}
