package com.company.customers;

import com.company.customers.model.Customer;
import com.company.customers.respository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomersRepositoryTests {
	@Autowired
	CustomerRepository customerRepo;

	@BeforeEach
	public void setUp() throws Exception {
		customerRepo.deleteAll();
	}

	@Test
	public void shouldAddCustomer() {
		//Arrange...
		Customer customer = new Customer();
		customer.setFirstName("Joe");
		customer.setLastName("Smith");
		customer.setPhone("111-222-3456");
		customer.setCompany("BigCo");
		customer.setCity("Chicago");
		customer.setAddress1("35353 1st Street");
		customer.setAddress2("22122 2nd Street");
		customer.setCountry("USA");
		customer.setPostalCode(22322);

		customerRepo.save(customer);


		//Assert...
		Optional<Customer> customer1 = customerRepo.findById(customer.getId());

		assertEquals(customer1.get(), customer);
	}

	@Test
	public void shouldUpdateCustomer() {
		//Arrange...
		Customer customer = new Customer();
		customer.setFirstName("Joe");
		customer.setLastName("Smith");
		customer.setPhone("111-222-3456");
		customer.setCompany("BigCo");
		customer.setCity("Chicago");
		customer.setAddress1("35353 1st Street");
		customer.setAddress2("22122 2nd Street");
		customer.setCountry("USA");
		customer.setPostalCode(22322);

		customerRepo.save(customer);

		Optional<Customer> customer1 = customerRepo.findById(customer.getId());

		assertEquals(customer1.get(), customer);

		customer.setFirstName("Joesph");
		customerRepo.save(customer);
		Optional<Customer> customer2 = customerRepo.findById(customer.getId());
		assertEquals(customer2.get(), customer);
	}

	@Test
	public void ShouldDeleteCustomer() {
		//Arrange...
		Customer customer = new Customer();
		customer.setFirstName("Joe");
		customer.setLastName("Smith");
		customer.setPhone("111-222-3456");
		customer.setCompany("BigCo");
		customer.setCity("Chicago");
		customer.setAddress1("35353 1st Street");
		customer.setAddress2("22122 2nd Street");
		customer.setCountry("USA");
		customer.setPostalCode(22322);

		customerRepo.save(customer);

		Optional<Customer> customer1 = customerRepo.findById(customer.getId());
		customerRepo.delete(customer);
		customer1 = customerRepo.findById(customer.getId());
		assertFalse(customer1.isPresent());
	}

	@Test
	public void shouldFindCustomersByState() {
		//Arrange...
		Customer customer = new Customer();
		customer.setFirstName("Joe");
		customer.setLastName("Smith");
		customer.setState("Virginia");

		Customer customer2 = new Customer();
		customer2.setFirstName("Mary");
		customer2.setLastName("Smith");
		customer2.setState("Virginia");
		customerRepo.save(customer);
		customerRepo.save(customer2);
		List<Customer> expectedCustomerList = new ArrayList<>(Arrays.asList(customer,customer2));


		//Assert...
		List<Customer> customerList = customerRepo.findAllByState("Virginia");

		assertEquals(customerList, expectedCustomerList);
	}
	@Test
	void contextLoads() {
	}

}
