package lk.ijse.carRental.service.impl;

import lk.ijse.carRental.dto.DriverDTO;
import lk.ijse.carRental.entity.Driver;
import lk.ijse.carRental.repo.DriverRepo;
import lk.ijse.carRental.service.DriverService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DriverServiceImpl implements DriverService {

    @Autowired
    DriverRepo driverRepo;

    @Autowired
    ModelMapper mapper;

    @Override
    public void addDriver(DriverDTO dto) {

        if (driverRepo.existsById(dto.getDriverId())) {
            throw new RuntimeException(dto.getDriverId() + " is already available, please insert a new ID");
        }

        Driver map = mapper.map(dto, Driver.class);
        //first param = source
        //Type you want to convert
        driverRepo.save(map);

    }

    @Override
    public void deleteDriver(String id) {
        if (!driverRepo.existsById(id)) {
            throw new RuntimeException(id + " Customer is not available, please check the ID before delete.!");
        }
        driverRepo.deleteById(id);
    }

    @Override
    public void updateDriver(DriverDTO dto) {
        if (!driverRepo.existsById(dto.getDriverId())) {
            throw new RuntimeException(dto.getDriverId() + " Customer is not available, please check the ID before update.!");
        }
        Driver map = mapper.map(dto, Driver.class);
        driverRepo.save(map);
    }

    @Override
    public List<Driver> getAllDrivers() {
        return driverRepo.findAll();
    }


}
