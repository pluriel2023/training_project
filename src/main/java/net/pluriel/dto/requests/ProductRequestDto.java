package net.pluriel.dto.requests;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ProductRequestDto {
    private String libelle;
    private BigDecimal price;
}
