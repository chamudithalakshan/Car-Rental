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
    private String reservedStatus;
    private String maintains;
    private List<String> imagePaths = new ArrayList<>();



}
