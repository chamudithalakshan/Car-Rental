package lk.ijse.carRental.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Customer {

    @Id
    private String nicNumber;

    @Column(unique = true)  // Assuming email is unique for each customer
    private String emailAddress;


    private String password;  // Remember to hash and salt passwords before storing!


    private String nicImage;


    private String drivingLicenseImage;


    private String name;


    private String address;


    private String contactNumber;


    private String activeStatus;


    private String role;

}
