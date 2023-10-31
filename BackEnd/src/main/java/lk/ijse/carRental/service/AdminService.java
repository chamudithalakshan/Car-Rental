package lk.ijse.carRental.service;

import lk.ijse.carRental.dto.AdminDTO;
import lk.ijse.carRental.dto.CustomerDTO;
import lk.ijse.carRental.entity.Admin;

public interface AdminService {

    AdminDTO findAdmin(String email, String password);
}
