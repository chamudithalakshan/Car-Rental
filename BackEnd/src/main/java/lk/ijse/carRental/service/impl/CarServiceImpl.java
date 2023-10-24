package lk.ijse.carRental.service.impl;

import lk.ijse.carRental.dto.CarDTO;
import lk.ijse.carRental.entity.Car;
import lk.ijse.carRental.repo.CarRepository;
import lk.ijse.carRental.service.CarService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CarRepository carRepository;

    @Value("${upload.dir}")
    private String uploadDir;

    @Transactional
    @Override
    public List<String> saveCar(CarDTO carDTO, MultipartFile[] photosOfCar) {
        List<String> savedImageUrls = new ArrayList<>();

        // Logic to save the car details in the database
        Car car = convertDtoToEntity(carDTO);

        for(MultipartFile photo : photosOfCar) {
            String originalFileName = StringUtils.cleanPath(photo.getOriginalFilename());
            String fileName = System.currentTimeMillis() + "_" + originalFileName; // Added a timestamp to make filename unique
            File dest = new File(uploadDir + File.separator + fileName);
            try {
                photo.transferTo(dest);
            } catch (IOException e) {
                // handle exception, e.g. throw a custom exception
                throw new RuntimeException("Failed to store file " + originalFileName, e);
            }

            // Save the image path in the car entity
            car.addImagePath(dest.getAbsolutePath());

            // Collect the image URLs
            savedImageUrls.add("/images/" + fileName);
        }

        carRepository.save(car);

        return savedImageUrls;
    }

    @Override
    public boolean deleteCar(String regNo) {
        if (carRepository.existsById(regNo)) {
            try {
                carRepository.deleteById(regNo);
                return true;
            } catch (Exception e) {
                // Logging exception details might be a good idea for diagnosing issues.
                e.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public boolean updateCar(String regNo, CarDTO carDTO) {
        if (carRepository.existsById(regNo)) {
            Car car = modelMapper.map(carDTO, Car.class);
            carRepository.save(car);
            return true;
        } else {
            return false;
        }
    }



    private Car convertDtoToEntity(CarDTO dto) {
        return modelMapper.map(dto, Car.class);
    }
}
