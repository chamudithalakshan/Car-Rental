package lk.ijse.carRental.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;



@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Driver {
    @Id
    private String driverId;
    private String driverName;
    private String driverTel;
    private String driverReserveStatus;
}
