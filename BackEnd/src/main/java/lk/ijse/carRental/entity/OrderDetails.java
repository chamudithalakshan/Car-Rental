package lk.ijse.carRental.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
//@IdClass()
public class OrderDetails {
    @Id
    private String id;  // <-- Changed to Long

    private String pickupDate;

    private String returnDate;

    private String pickupLocation;

    private String returnLocation;

    private String driverStatus;

    private String loseDamage;

    private String bankReciept;

    private String OrderStatus;

    private String  carId;

}
