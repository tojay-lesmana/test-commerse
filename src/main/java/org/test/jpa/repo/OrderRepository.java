package org.test.jpa.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.test.jpa.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

	public Order findById(Long id);
	
}
