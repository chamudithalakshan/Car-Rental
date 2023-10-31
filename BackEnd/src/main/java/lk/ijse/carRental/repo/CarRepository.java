package lk.ijse.carRental.repo;

import lk.ijse.carRental.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, String> {

    Car findByRegNo(String regNo);
}
