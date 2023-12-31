package lk.ijse.carRental.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class DriverDTO {

    private String driverId;
    private String userName;
    private String password;
    private String driverName;
    private String driverTel;
    private String driverReserveStatus;

}
