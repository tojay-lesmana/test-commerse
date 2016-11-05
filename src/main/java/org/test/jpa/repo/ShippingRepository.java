package org.test.jpa.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.test.jpa.model.Shipping;

public interface ShippingRepository extends JpaRepository<Shipping, Long>{

}
