package com.enumcrm.repository;

import com.enumcrm.domain.Company;
import com.enumcrm.domain.Customer;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@DataJpaTest
@RunWith(SpringRunner.class)
public class CustomerRepositoryTest {

  public static final String ENUM_CRM = "EnumCRM";
  @Autowired
  private CompanyRepository companyRepository;

  @Autowired
  private CustomerRepository underTest;

  @Test
  public void shouldReturnAllCustomersWithCompany() {
    Customer customer1 = givenCustomer(
        "Test",
        "User1",
        "testuser1@example.com",
        enumCRMCompany());
    Customer customer2 = givenCustomer(
        "Test",
        "User2",
        "testuser2@example.com",
        enumCRMCompany());
    Customer customer3 = givenCustomer(
        "Test",
        "User3",
        "testuser3@example.com",
        null);
    List<Customer> result = underTest.findCustomersWithCompany();
    assertThat(result, is(Lists.newArrayList(customer1, customer2)));
  }

  private Customer givenCustomer(String firstName, String lastName, String email, Company company) {
    return underTest.save(new Customer(firstName, lastName, email, company));
  }

  private Company enumCRMCompany() {
    return companyRepository.findByCompanyNameIgnoreCase(ENUM_CRM)
        .orElse(new Company(ENUM_CRM));
  }
}