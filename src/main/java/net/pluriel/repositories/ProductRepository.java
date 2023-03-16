package net.pluriel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.pluriel.entities.Product;
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

}
