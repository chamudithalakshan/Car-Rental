package lk.ijse.carRental.repo;

import lk.ijse.carRental.entity.Customer;
import lk.ijse.carRental.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepo extends JpaRepository<Orders,Long> {

    List<Orders> findByCusID(String customerId);

    List<Orders> findByCusID(Customer customer);


}
