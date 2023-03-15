package net.pluriel.dto.responses;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ProductResponseDto {
    private Integer id;
    private String libelle;
    private BigDecimal price;
}
