package lk.ijse.carRental.controller;

import lk.ijse.carRental.dto.CarDTO;
import lk.ijse.carRental.service.CarService;
import lk.ijse.carRental.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/Car")
@CrossOrigin

public class CarController {

    @Autowired
    private CarService carService;

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


}
