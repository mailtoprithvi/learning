package com.enumcrm;

import com.enumcrm.domain.Customer;
import com.enumcrm.dto.CustomerDTO;
import com.enumcrm.repository.CustomerRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class EnumCrmIT {

  @LocalServerPort
  private int port;

  @Autowired
  private CustomerRepository customerRepository;

  @Before
  public void setup() {
    customerRepository.deleteAll();
  }

  private TestRestTemplate restTemplate = new TestRestTemplate();

  @Test
  public void shouldReturnCustomerByEmail() {
    givenCustomer("Test", "User1", "testuser1@example.com");
    givenCustomer("Test2", "User2", "testuser2@example.com");
    ResponseEntity<CustomerDTO> response = getCustomerByEmail("testuser2@example.com");
    assertTrue(response.getStatusCode().is2xxSuccessful());
    CustomerDTO result = response.getBody();
    assertThat(result.getFirstName(), is("Test2"));
    assertThat(result.getLastName(), is("User2"));
    assertThat(result.getEmail(), is("testuser2@example.com"));
    assertThat(result.getId(), is(not(nullValue())));
  }

  private ResponseEntity<CustomerDTO> getCustomerByEmail(String email) {
    final String url = String.format("http://localhost:%s/customers?email=%s", port, email);
    return restTemplate.getForEntity(url, CustomerDTO.class);
  }

  private void givenCustomer(String firstName, String lastName, String email) {
    customerRepository.save(new Customer(firstName, lastName, email, null));
  }
}
