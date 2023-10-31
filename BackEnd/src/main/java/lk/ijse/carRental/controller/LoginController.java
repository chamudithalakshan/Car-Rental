package lk.ijse.carRental.controller;


import lk.ijse.carRental.dto.AdminDTO;
import lk.ijse.carRental.dto.CustomerDTO;
import lk.ijse.carRental.dto.DriverDTO;
import lk.ijse.carRental.dto.LoginDTO;
import lk.ijse.carRental.service.AdminService;
import lk.ijse.carRental.service.CustomerService;
import lk.ijse.carRental.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/Login")
@CrossOrigin
public class LoginController {
    @Autowired
    CustomerService customerService;

    AdminService adminService;

    @PostMapping
    public ResponseEntity<?> loginUser(LoginDTO dto) {

        if (dto.getUserType().equals("Customer")){
            CustomerDTO customer = customerService.findCustomer(dto.getEmail(), dto.getPassword());
//            System.out.println(customer);
            return ResponseEntity.ok(customer);

        }else if (dto.getUserType().equals("Admin")){
            System.out.println(dto.getEmail()+" "+dto.getPassword());
            AdminDTO admin = adminService.findAdmin(dto.getEmail(), dto.getPassword());
            return ResponseEntity.ok(admin);




        }else if (dto.getUserType().equals("Driver")){

            System.out.println("im  Driver");
        }

        return new ResponseEntity<>("Successfully Added", HttpStatus.OK);
    }



}
