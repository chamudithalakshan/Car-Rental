package lk.ijse.carRental.repo;

import lk.ijse.carRental.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer,String> {

 Customer findCustomerByEmailAddressAndPassword(String email,String password);

 Customer findCustomerByNicNumber(String nic);


}
