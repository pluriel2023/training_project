package net.pluriel.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String libelle;
    
    @Column(name = "price", columnDefinition = "Decimal(14,2) default 0.00")
    private BigDecimal price;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    @JsonIgnoreProperties({"product"})
    @OrderBy("id ASC")
    private List<Order> orders;
    
    @CreationTimestamp
	@Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
	
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
