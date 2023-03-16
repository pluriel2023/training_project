package net.pluriel.entities;

import java.time.LocalDateTime;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
    
    @CreationTimestamp
	@Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
	
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
