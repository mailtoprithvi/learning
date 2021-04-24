package com.enumcrm.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Entity
public class Customer {

  @Id
  @GeneratedValue
  private UUID id;

  private String firstName;
  private String lastName;

  @Column(unique = true)
  private String email;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "company_id")
  private Company company;

  public Customer() {
  }

  public Customer(String firstName, String lastName, String email, Company company) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.company = company;
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

  public UUID getId() {
    return id;
  }

  public Company getCompany() {
    return company;
  }

  public String toString() {
    return String.format(
        "Customer [firstName=%s; lastName=%s; email=%s]",
        firstName,
        lastName,
        email);
  }
}
