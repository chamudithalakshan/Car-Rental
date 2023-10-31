package lk.ijse.carRental.repo;

import lk.ijse.carRental.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Orders,Long> {

}
