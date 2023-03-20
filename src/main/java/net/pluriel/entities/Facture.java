package net.pluriel.entities;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "factures")
@Data
@NoArgsConstructor
public class Facture {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_payment_facture")
    private Date datePaymentFacture;
	
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "facture")
    @JsonIgnoreProperties({"facture"})
    @OrderBy("id ASC")
    private List<Order> orders;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "facture")
    @JsonIgnoreProperties({"facture"})
    @OrderBy("id ASC")
    private List<PaymentFacture> paymentFactures;
    @CreationTimestamp
	@Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
	
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
