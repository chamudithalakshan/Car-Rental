package lk.ijse.carRental.repo;

import lk.ijse.carRental.entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailsRepo extends JpaRepository<OrderDetails,Long> {
}
