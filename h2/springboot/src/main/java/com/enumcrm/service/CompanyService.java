package com.enumcrm.service;

import com.enumcrm.domain.Company;
import com.enumcrm.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompanyService {

  private final CompanyRepository companyRepository;

  @Autowired
  public CompanyService(CompanyRepository companyRepository) {
    this.companyRepository = companyRepository;
  }

  public Optional<Company> getCompany(String companyName) {
    return companyRepository.findByCompanyNameIgnoreCase(companyName);
  }
}
