package lk.ijse.carRental.service.impl;

import lk.ijse.carRental.dto.CarDTO;
import lk.ijse.carRental.dto.CarResponseDTO;
import lk.ijse.carRental.entity.Car;
import lk.ijse.carRental.repo.CarRepository;
import lk.ijse.carRental.service.CarService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


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

        for (MultipartFile photo : photosOfCar) {
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

    @Transactional
    @Override

    public void updateCarDetails(CarDTO carDTO) {
        if (!carRepository.existsById(carDTO.getRegNo())) {
            throw new RuntimeException(carDTO.getRegNo() + " Customer is not available, please check the ID before update.!");
        }
        Car map = modelMapper.map(carDTO, Car.class);
        carRepository.save(map);
    }


    //    public boolean updateCar(String regNo, CarDTO carDTO) {
////        System.out.println("Car dto :"+carDTO);
//
//        Optional<Car> carOptional = carRepository.findById(regNo);
//
//
//
//        if (carOptional.isPresent()) {
//            Car existingCar = carOptional.get();
//
//            if (carDTO.getVehicleBrand() != null && !carDTO.getVehicleBrand().isEmpty()) {
//                existingCar.setVehicleBrand(carDTO.getVehicleBrand());
//            }
//            if (carDTO.getVehicleType() != null && !carDTO.getVehicleType().isEmpty()) {
//                existingCar.setVehicleType(carDTO.getVehicleType());
//            }
//            if (carDTO.getNumberOfPassengers() != null) {
//                existingCar.setNumberOfPassengers(carDTO.getNumberOfPassengers());
//            }
//            if (carDTO.getTransmissionType() != null && !carDTO.getTransmissionType().isEmpty()) {
//                existingCar.setTransmissionType(carDTO.getTransmissionType());
//            }
//            if (carDTO.getDailyRentalPrice() != null) {
//                existingCar.setDailyRentalPrice(carDTO.getDailyRentalPrice());
//            }
//            if (carDTO.getMonthlyRentalPrice() != null) {
//                existingCar.setMonthlyRentalPrice(carDTO.getMonthlyRentalPrice());
//            }
//            if (carDTO.getFuelType() != null && !carDTO.getFuelType().isEmpty()) {
//                existingCar.setFuelType(carDTO.getFuelType());
//            }
//            if (carDTO.getFreeMileage() != null) {
//                existingCar.setFreeMileage(carDTO.getFreeMileage());
//            }
//            if (carDTO.getPriceForExtraKM() != null) {
//                existingCar.setPriceForExtraKM(carDTO.getPriceForExtraKM());
//            }
//            if (carDTO.getVehicleColor() != null && !carDTO.getVehicleColor().isEmpty()) {
//                existingCar.setVehicleColor(carDTO.getVehicleColor());
//            }
//            if (carDTO.getReservedStatus() != null && !carDTO.getReservedStatus().isEmpty()) {
//                existingCar.setReservedStatus(carDTO.getReservedStatus());
//            }
//            if (carDTO.getMaintains() != null && !carDTO.getMaintains().isEmpty()) {
//                existingCar.setMaintains(carDTO.getMaintains());
//            }
//            if (carDTO.getImagePaths() != null && !carDTO.getImagePaths().isEmpty()) {
//                existingCar.setImagePaths(carDTO.getImagePaths());
//            }
//            // ... Add any other fields present in your DTO and Car entity ...
//
//            carRepository.save(existingCar);
//            return true;
//        } else {
//            return false;
//        }
//    }
    @Transactional
    @Override
    public List<CarResponseDTO> getAllCars() {
        List<Car> cars = carRepository.findAll();
        return cars.stream().map(car -> new CarResponseDTO(
                car.getRegNo(),
                car.getVehicleBrand(),
                car.getVehicleType(),
                car.getNumberOfPassengers(),
                car.getTransmissionType(),
                car.getDailyRentalPrice(),
                car.getMonthlyRentalPrice(),
                car.getFuelType(),
                car.getFreeMileage(),
                car.getPriceForExtraKM(),
                car.getVehicleColor(),
                car.getReservedStatus(),
                car.getMaintains(),
                car.getImagePaths()
        )).collect(Collectors.toList());
    }


    @Override
    public Resource loadFileAsResource(String filename) {
        try {
            Path filePath = Paths.get(uploadDir).resolve(filename).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new RuntimeException("File not found: " + filename);
            }
        } catch (MalformedURLException ex) {
            throw new RuntimeException("File not found: " + filename, ex);
        }
    }


    private Car convertDtoToEntity(CarDTO dto) {
        return modelMapper.map(dto, Car.class);
    }
}
