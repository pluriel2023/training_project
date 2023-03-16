package net.pluriel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;


import net.pluriel.entities.Order;

public interface OrderRepository  extends JpaRepository<Order, Integer>{

}
