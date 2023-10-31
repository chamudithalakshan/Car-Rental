package lk.ijse.carRental.controller;

import lk.ijse.carRental.dto.CarDTO;
import lk.ijse.carRental.dto.CarResponseDTO;
import lk.ijse.carRental.entity.Car;
import lk.ijse.carRental.service.CarService;
import lk.ijse.carRental.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/Car")
@CrossOrigin

public class CarController {



    @Autowired
    private CarService carService;

    @Value("${upload.dir}")
    private String uploadDir;

    @PostMapping
    public ResponseEntity<?> saveCar(@ModelAttribute CarDTO carDTO,
                                     @RequestParam("photosOfCar") MultipartFile[] photosOfCar) {
        try {
            List<String> savedImageUrls = carService.saveCar(carDTO, photosOfCar);
            System.out.println(savedImageUrls.toString());
            return ResponseEntity.ok(savedImageUrls);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving car details.");
        }
    }

    @DeleteMapping(params = {"regNo"})
    public ResponseEntity<String> deleteCar( String regNo) {
        System.out.println(regNo);
        boolean isDeleted = carService.deleteCar(regNo);
        if (isDeleted) {
            return new ResponseEntity<>("Car with regNo: " + regNo + " has been successfully deleted.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to delete the car with regNo: " + regNo, HttpStatus.NOT_FOUND);
        }
    }

//    @PutMapping("/{regNo}")
//    public ResponseEntity<String> updateCar(@PathVariable String regNo, @RequestBody CarDTO carDTO) {
//        try {
//            System.out.println("Car dto in controller"+carDTO);
//            boolean isUpdated = carService.updateCar(regNo, carDTO);
//            if (isUpdated) {
//                return new ResponseEntity<>("Car with regNo: " + regNo + " has been successfully updated.", HttpStatus.OK);
//            } else {
//                return new ResponseEntity<>("Failed to update the car with regNo: " + regNo, HttpStatus.BAD_REQUEST);
//            }
//        } catch (Exception e) {
//            return new ResponseEntity<>("Error updating car details. Exception: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @PutMapping("/update")
    public ResponseUtil updateCar(@RequestBody CarDTO carDTO) {
        System.out.println("Car dto in controller" + carDTO);
        carService.updateCarDetails(carDTO);  // Example method in your service
        return new ResponseUtil("Ok","Successfully Updated",carDTO);



    }

    @GetMapping
    public ResponseEntity<List<CarResponseDTO>> getAllCars() {
        List<CarResponseDTO> carDTOs = carService.getAllCars();
        if (carDTOs != null && !carDTOs.isEmpty()) {
            return new ResponseEntity<>(carDTOs, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/image/{filename:.+}")
    public ResponseEntity<Resource> serveImage(@PathVariable String filename) {
        Path path = Paths.get(uploadDir, filename);
        Resource file = new FileSystemResource(path);
        if (file.exists()) {
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                    .body(file);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


    @GetMapping("/allCars")
    public ResponseEntity<List<CarDTO>> getAllCar() {
        List<CarDTO> cars = carService.getAllCar();
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }


    @GetMapping("/{regNo}")
    public ResponseEntity<CarDTO> getCarByRegNo(@PathVariable String regNo) {
        System.out.println(regNo);
        CarDTO car = carService.getCarByRegNo(regNo);
        if (car != null) {
            return new ResponseEntity<>(car, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
