package com.enumcrm.dto;

import com.enumcrm.domain.Company;
import com.enumcrm.domain.Customer;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class CustomerDTO {

  private final UUID id;
  private final String firstName;
  private final String lastName;
  private final String email;
  private final String companyName;

  @JsonCreator
  public CustomerDTO(@JsonProperty("id") UUID id,
                     @JsonProperty("firstName") String firstName,
                     @JsonProperty("lastName") String lastName,
                     @JsonProperty("email") String email,
                     @JsonProperty("companyName") String companyName) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.companyName = companyName;
  }

  public UUID getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getEmail() {
    return email;
  }

  public String getCompanyName() {
    return companyName;
  }

  public static CustomerDTO toCustomerDTO(Customer customer) {
    return new CustomerDTO(
        customer.getId(),
        customer.getFirstName(),
        customer.getLastName(),
        customer.getEmail(),
        toCompanyName(customer.getCompany()));
  }

  private static String toCompanyName(Company company) {
    return company != null ? company.getCompanyName() : null;
  }
}
