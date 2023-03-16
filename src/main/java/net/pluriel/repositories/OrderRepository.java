package net.pluriel.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.pluriel.entities.Order;

public interface OrderRepository  extends JpaRepository<Order, Integer>{

	@Query("select orderObj from Order orderObj where orderObj.client.id = ?1")
	Page<Order> getAllByClient(Integer clientId, Pageable pageable);
	
	@Query(nativeQuery = true, value = "select o.* from client_product_orders o where o.client_id = ?1")
	Page<Order> getAllByClient2(Integer clientId, Pageable pageable);
}
