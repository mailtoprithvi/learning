package com.enumcrm.domain;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Company {

  @Id
  @GeneratedValue
  @Type(type = "uuid-char")
  private UUID id;

  @Column(unique = true)
  private String companyName;

  public Company() {
  }

  public Company(String companyName) {
    this.companyName = companyName;
  }

  public UUID getId() {
    return id;
  }

  public String getCompanyName() {
    return companyName;
  }
}
