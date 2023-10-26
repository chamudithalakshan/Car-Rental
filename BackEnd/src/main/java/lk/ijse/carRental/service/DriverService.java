package lk.ijse.carRental.service;

import lk.ijse.carRental.dto.DriverDTO;

public interface DriverService {


    void addDriver(DriverDTO dto);

    void deleteDriver(String id);

    void updateDriver(DriverDTO dto);
}
