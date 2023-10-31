package lk.ijse.carRental.entity;


import lk.ijse.carRental.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Orders {
    @Id
    private String oid;  // <-- Changed to Long
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customerID")
    private Customer cusID;
    private LocalDate date;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "orderDetailsId")
    private OrderDetails orderDetails;
}
