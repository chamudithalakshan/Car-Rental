package lk.ijse.carRental.service;

import lk.ijse.carRental.dto.CarDTO;
import lk.ijse.carRental.dto.CarResponseDTO;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CarService {
    List<String> saveCar(CarDTO carDTO, MultipartFile[] photosOfCar);

    boolean deleteCar(String regNo);

    void updateCarDetails(CarDTO carDTO);

     List<CarResponseDTO> getAllCars();

    public List<CarDTO> getAllCar();
    Resource loadFileAsResource(String filename);

    CarDTO getCarByRegNo(String regNo);

//    boolean updateCar(String regNo, CarDTO carDTO);
}
