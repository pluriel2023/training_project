package net.pluriel.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "payment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private int idPayment;

	  @Column(name = "mode")
	  @Enumerated(EnumType.STRING)
	  private Mode mode;
	  
	  @CreationTimestamp
	  @Column(name = "created_at", updatable = false)
	  private LocalDateTime createdAt;
		
	  @UpdateTimestamp
	  @Column(name = "updated_at")
	  private LocalDateTime updatedAt;
	  
	  
}
