package lk.ijse.carRental.service.impl;

import lk.ijse.carRental.dto.AdminDTO;
import lk.ijse.carRental.dto.CustomerDTO;
import lk.ijse.carRental.entity.Admin;
import lk.ijse.carRental.repo.AdminRepo;
import lk.ijse.carRental.service.AdminService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminRepo adminRepo;

    @Autowired
    ModelMapper mapper;


    @Override
    public AdminDTO findAdmin(String email, String password) {
        Admin adminByEmailAddress = adminRepo.findAdminByEmailAddressAndPassword(email,password);

        if (adminByEmailAddress == null) {
            throw new RuntimeException("Admin not found with email: " + email);
        }

        // If you have a password hashing mechanism, use it before comparing.
        // For this example, I'm directly comparing the plain text password (not recommended for production).
        if (!adminByEmailAddress.getPassword().equals(password)) {
            throw new RuntimeException("Invalid password");
        }

        System.out.println(adminByEmailAddress);
        return mapper.map(adminByEmailAddress, AdminDTO.class);
    }
}
