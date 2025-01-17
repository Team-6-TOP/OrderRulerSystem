package testing;

import Enums.CustomerType;
import models.CustomerModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import repositories.CustomerRepository;
import services.CustomerService;

import static org.junit.Assert.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


import static org.junit.Assert.assertEquals;

public class CustomerServiceTest {
    private CustomerService customerService;
    private final String TEST_CUST = "test_customers.txt";

    @Before
    public void start() {
        CustomerRepository customerRepository = new CustomerRepository(TEST_CUST);
        customerService = new CustomerService(customerRepository);
    }

    @After
    public void clearTest() throws Exception {
        Path path = Paths.get(TEST_CUST);
        Files.deleteIfExists(path);
    }

    @Test
    public void testAddCustomer() {
        CustomerModel test1 = new CustomerModel(1, "Carl", CustomerType.NEW);
        CustomerModel test11 = new CustomerModel(2, "Jackson", CustomerType.VIP);
        customerService.addCustomer(test1);
        customerService.addCustomer(test11);

        List<CustomerModel> customers = customerService.getAll();
        assertEquals(2, customers.size());
        assertEquals(test1, customers.get(0));
        assertEquals(test11, customers.get(1));
    }

    @Test
    public void testGetAllCustomers() {
        CustomerModel test2 = new CustomerModel(10, "Charley", CustomerType.NEW);
        CustomerModel test22 = new CustomerModel(20, "Chloe", CustomerType.REGULAR);
        CustomerModel test222 = new CustomerModel(30, "Charlotte", CustomerType.VIP);

        customerService.addCustomer(test2);
        customerService.addCustomer(test22);
        customerService.addCustomer(test222);

        List<CustomerModel> allCustomers = customerService.getAll();
        assertEquals(3, allCustomers.size());
        assertTrue(allCustomers.contains(test2));
        assertTrue(allCustomers.contains(test22));
        assertTrue(allCustomers.contains(test222));
    }

    @Test
    public void testGetCustomerById() {
        CustomerModel test3 = new CustomerModel(11, "Nick", CustomerType.NEW);
        CustomerModel test33 = new CustomerModel(21, "Nathan", CustomerType.REGULAR);
        CustomerModel test333 = new CustomerModel(31, "Nicolle", CustomerType.VIP);

        customerService.addCustomer(test3);
        customerService.addCustomer(test33);
        customerService.addCustomer(test333);

        CustomerModel customerId = customerService.getById(31);
        assertEquals(test333, customerId);
    }

    @Test
    public void testGetCustomerById_notFound() {
        try {
            customerService.getById(80);
            fail("Ожидалось исключение при поиске несуществующего покупателя");
        } catch (RuntimeException e) {
            String trueFalse = "Покупатель с ID 80 не найден";
            assertTrue(e.getMessage().contains(trueFalse));
        }
    }
}
