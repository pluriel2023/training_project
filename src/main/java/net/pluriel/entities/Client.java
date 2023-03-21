package net.pluriel.entities;

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
@Table(name = "clients")
@Data
@NoArgsConstructor
public class Client {
	
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false,unique = true ,length = 65, name = "name")
    private String name;
    
    @Column(nullable = false, length = 100, name = "email")
    private String email;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "client")
    @JsonIgnoreProperties({"client"})
    @OrderBy("id ASC")
    private List<Order> orders;
    
    @CreationTimestamp
	@Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
	
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
