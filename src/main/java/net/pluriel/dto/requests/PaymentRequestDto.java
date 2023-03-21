package net.pluriel.dto.requests;



import lombok.Data;
import lombok.NoArgsConstructor;
import net.pluriel.entities.Mode;

@Data
@NoArgsConstructor
public class PaymentRequestDto {

	private Mode mode;
}
