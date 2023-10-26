package lk.ijse.carRental.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarResponseDTO {
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
    private String vehicleColor;
    private String reservedStatus;
    private String maintains;
    private List<String> imagePaths;
}
