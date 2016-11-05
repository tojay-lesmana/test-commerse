package org.test.jpa.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.test.jpa.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	public Product findById(Long id);
	
	public List<Product> findByStockGreaterThan(int stock);
	
}
