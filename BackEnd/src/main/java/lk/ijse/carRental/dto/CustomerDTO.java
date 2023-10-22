package lk.ijse.carRental.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class CustomerDTO {


    private String nicNumber;


    private String emailAddress;


    private String password;


    private MultipartFile nicImage;

    private MultipartFile drivingLicenseImage;


    private String name;


    private String address;


    private String contactNumber;


    private String activeStatus;


    private String role;
}
