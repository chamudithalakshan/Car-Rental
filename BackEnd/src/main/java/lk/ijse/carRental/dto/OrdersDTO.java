package lk.ijse.carRental.dto;

import lk.ijse.carRental.entity.Customer;
import lk.ijse.carRental.entity.OrderDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class OrdersDTO {
    private String customerID;
    private String date;
    private MultipartFile bankReciept;
}
