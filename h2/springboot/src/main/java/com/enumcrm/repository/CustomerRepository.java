package com.enumcrm.repository;

import com.enumcrm.domain.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepository
    extends CrudRepository<Customer, Long> {

  Customer findByEmail(String email);

  @Query("select cu from Customer cu inner join Company co on cu.company.id = co.id")
  List<Customer> findCustomersWithCompany();
}
