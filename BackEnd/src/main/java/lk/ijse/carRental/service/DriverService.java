package lk.ijse.carRental.service;

import lk.ijse.carRental.dto.DriverDTO;
import lk.ijse.carRental.entity.Driver;

import java.util.List;

public interface DriverService {


    void addDriver(DriverDTO dto);

    void deleteDriver(String id);

    void updateDriver(DriverDTO dto);

    List<Driver> getAllDrivers();


}
