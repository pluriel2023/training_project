package net.pluriel.dto.responses;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class PaymentFactureResponseDto {
    private Integer id;
    private BigDecimal quantity;

    private PaymentResponseDto payment;
    private FactureResponseDto facture;
}
