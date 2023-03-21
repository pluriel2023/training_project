package net.pluriel.entities;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "client_product_orders")
@Data
@NoArgsConstructor
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
   
    private int quantity;
	
	@ManyToOne
	@JoinColumn(name = "client_id")
	@JsonIgnoreProperties({"orders"})
	private Client client;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	@JsonIgnoreProperties({"orders"})
	private Product product;
	
	@ManyToOne
	@JoinColumn(name = "facture_id")
	@JsonIgnoreProperties({"orders"})
	private Facture facture;
	
    @CreationTimestamp
	@Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
	
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
