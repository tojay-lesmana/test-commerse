package org.test.jpa.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.test.jpa.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long>{

}
