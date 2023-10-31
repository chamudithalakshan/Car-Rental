package lk.ijse.carRental.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Driver {
    @Id
    private String driverId;
    private String userName;
    private String password;
    private String driverName;
    private String driverTel;
    private String driverReserveStatus;

//    @OneToMany(mappedBy = "driver")
//    private List<Orders> reservations = new ArrayList<>();
}
