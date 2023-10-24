package lk.ijse.carRental.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Data                   // Lombok annotation to generate getters, setters, and other utility methods
@NoArgsConstructor      // Lombok annotation for the no-args constructor
@AllArgsConstructor     // Lombok annotation for the all-args constructor
@ToString
public class CarDTO {

    private String regNo;
    private String vehicleBrand;
    private String vehicleType;
    private Integer numberOfPassengers;
    private String transmissionType;
    private Double dailyRentalPrice;
    private Double monthlyRentalPrice;
    private String fuelType;
    private Double freeMileage;
    private Double priceForExtraKM;
    private String VehicleColor;
    private String reservedStatus; // I'm assuming this to be a boolean. Adjust if required.
    private String maintains;      // Assuming this to be a boolean. Adjust if required.
    private List<String> imagePaths = new ArrayList<>(); // Paths to the stored images

    // Add other fields and related annotations if required.

    // Note: Lombok's @Data annotation will generate getters, setters, equals, hashCode, and toString methods.

}
