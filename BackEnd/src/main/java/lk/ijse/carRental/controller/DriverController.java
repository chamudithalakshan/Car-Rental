package lk.ijse.carRental.controller;


import lk.ijse.carRental.dto.DriverDTO;
import lk.ijse.carRental.entity.Driver;
import lk.ijse.carRental.service.DriverService;
import lk.ijse.carRental.util.ResponseUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Driver")
@CrossOrigin
public class DriverController {

    @Autowired
    private DriverService driverService;

    @Autowired
    ModelMapper modelMapper;


    @PostMapping
    public ResponseUtil addDriver(DriverDTO dto){
        driverService.addDriver(dto);
        return new ResponseUtil("Ok","Successfully Added",dto);
    }

    @DeleteMapping(params = {"DriverId"})
    public ResponseUtil deleteDriver(String DriverId){
        driverService.deleteDriver(DriverId);
        return new ResponseUtil("Ok","Successfully Deleted",DriverId);
    }

    @PutMapping("/update")
    public ResponseUtil updateDriver(@RequestBody DriverDTO dto){
        driverService.updateDriver(dto);
        return new ResponseUtil("Ok","Successfully Updated",dto);
    }

    @GetMapping
    public List<Driver> getAllDrivers() {
        return driverService.getAllDrivers();
    }
}
