package org.test.jpa.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.test.jpa.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{
	
	public Account findByEmail(String email);
	public Account findById(Long id);	

}
