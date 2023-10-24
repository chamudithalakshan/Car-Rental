package lk.ijse.carRental.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Car {
    @Id
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

    @ElementCollection
    private List<String> imagePaths = new ArrayList<>();

    public void addImagePath(String path) {
        this.imagePaths.add(path);
    }// Paths to the stored images

    // Add other fields and related annotations if required.

    // Note: Lombok's @Data annotation will generate getters, setters, equals, hashCode, and toString methods.

}
