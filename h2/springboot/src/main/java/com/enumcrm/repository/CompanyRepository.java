package com.enumcrm.repository;

import com.enumcrm.domain.Company;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface CompanyRepository extends CrudRepository<Company, UUID> {
  Optional<Company> findByCompanyNameIgnoreCase(String companyName);
}
