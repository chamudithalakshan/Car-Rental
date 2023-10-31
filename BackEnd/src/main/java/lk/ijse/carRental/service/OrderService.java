package lk.ijse.carRental.service;

import lk.ijse.carRental.dto.OrderDetailsDTO;
import lk.ijse.carRental.dto.OrdersDTO;
import org.springframework.web.multipart.MultipartFile;

public interface OrderService {
    void addOrder(OrdersDTO dto, OrderDetailsDTO orderDetailsDTO);
}
