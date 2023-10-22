package lk.ijse.carRental.service;

import lk.ijse.carRental.dto.CustomerDTO;
import lk.ijse.carRental.entity.Customer;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CustomerService {
    void addCustomer(CustomerDTO dto, MultipartFile nicImage, MultipartFile drivingLicenseImage);

    void deleteCustomer(String id);

    List<CustomerDTO> getAllCustomer();

    CustomerDTO findCustomer(String id);

    void updateCustomer(CustomerDTO c);

    List<Customer> getAllCustomers();
}