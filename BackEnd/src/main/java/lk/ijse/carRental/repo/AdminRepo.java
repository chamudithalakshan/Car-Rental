package lk.ijse.carRental.repo;

import lk.ijse.carRental.entity.Admin;
import lk.ijse.carRental.entity.Car;
import lk.ijse.carRental.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepo extends JpaRepository<Admin, String> {

    Admin findAdminByEmailAddressAndPassword(String email,String password);
}
