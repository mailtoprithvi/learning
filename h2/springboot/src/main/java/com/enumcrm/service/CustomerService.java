package com.enumcrm.service;

import com.enumcrm.domain.Company;
import com.enumcrm.domain.Customer;
import com.enumcrm.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CustomerService {

  private final CustomerRepository customerRepository;
  private final CompanyService companyService;

  @Autowired
  public CustomerService(CustomerRepository customerRepository,
                         CompanyService companyService) {
    this.customerRepository = customerRepository;
    this.companyService = companyService;
  }

  public Customer createCustomer(String firstName,
                                 String lastName,
                                 String email,
                                 String companyName) {
    Company company = companyService
        .getCompany(companyName)
        .orElse(newCompany(companyName));

    return customerRepository.save(
        new Customer(
            firstName,
            lastName,
            email,
            company));
  }

  private Company newCompany(String companyName) {
    return companyName != null ? new Company(companyName) : null;
  }

  public List<Customer> getCustomers() {
    return StreamSupport.stream(
        customerRepository
            .findAll()
            .spliterator(), false)
        .collect(Collectors.toList());
  }

  public Customer getByEmail(String email) {
    return customerRepository.findByEmail(email);
  }

  public List<Customer> getCustomersWithCompany() {
    return customerRepository.findCustomersWithCompany();
  }
}
