package lk.ijse.carRental.service;

import lk.ijse.carRental.dto.CarDTO;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CarService {
    List<String> saveCar(CarDTO carDTO, MultipartFile[] photosOfCar);

    boolean deleteCar(String regNo);

    @Transactional
    void updateCarDetails(CarDTO carDTO);

//    boolean updateCar(String regNo, CarDTO carDTO);
}
