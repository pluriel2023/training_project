package net.pluriel.dto.requests;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class PaymentFactureRequestDto {
    private Integer id;
    private BigDecimal quantity;
    private RequestIdDto facture;
    private RequestIdDto payment;

}
