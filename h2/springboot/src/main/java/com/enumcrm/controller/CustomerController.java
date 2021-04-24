package com.enumcrm.controller;

import com.enumcrm.domain.Customer;
import com.enumcrm.dto.CustomerDTO;
import com.enumcrm.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static com.enumcrm.dto.CustomerDTO.toCustomerDTO;

@RestController
public class CustomerController {

  private final CustomerService customerService;

  @Autowired
  public CustomerController(CustomerService customerService) {
    this.customerService = customerService;
  }

  @PostMapping(value = "/customers")
  @ResponseBody
  public CustomerDTO createCustomer(String firstName,
                                    String lastName,
                                    String email,
                                    String company) {
    Customer customer = customerService
        .createCustomer(firstName, lastName, email, company);
    return toCustomerDTO(customer);
  }

  @GetMapping(value = "/customers")
  @ResponseBody
  public List<CustomerDTO> getAllCustomers() {
    return customerService.getCustomers()
        .stream()
        .map(CustomerDTO::toCustomerDTO)
        .collect(Collectors.toList());
  }

  @GetMapping(value = "/customers", params = "email")
  @ResponseBody
  public CustomerDTO getByEmail(@RequestParam("email") String email) {
    Customer customer = customerService.getByEmail(email);
    return toCustomerDTO(customer);
  }

  @GetMapping(value = "/customers", params = "withCompanyOnly")
  @ResponseBody
  public List<CustomerDTO> getAllCustomersWithCompany(
      @RequestParam("withCompanyOnly") boolean withCompanyOnly) {
    return customerService.getCustomersWithCompany()
        .stream()
        .map(CustomerDTO::toCustomerDTO)
        .collect(Collectors.toList());
  }
}
