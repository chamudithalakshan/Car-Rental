package lk.ijse.carRental.dto;

import lk.ijse.carRental.entity.Car;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;


@Data                   // Lombok annotation to generate getters, setters, and other utility methods
@NoArgsConstructor      // Lombok annotation for the no-args constructor
@AllArgsConstructor     // Lombok annotation for the all-args constructor
@ToString
public class OrderDetailsDTO {

    private String pickupDate;

    private String returnDate;

    private String pickupLocation;

    private String returnLocation;

    private String driverStatus;

    private String loseDamage;

//    private MultipartFile bankReciept;

    private String OrderStatus;

    private String carRegNo;

}
