package lk.ijse.carRental.service;

import lk.ijse.carRental.dto.OrderDetailsDTO;
import lk.ijse.carRental.dto.OrdersDTO;
import lk.ijse.carRental.entity.Customer;
import lk.ijse.carRental.entity.Orders;

import java.util.List;

public interface OrderService {
    void addOrder(OrdersDTO dto, OrderDetailsDTO orderDetailsDTO);

    List<Orders> getAllOrders();


    List<Orders> getOrdersByCustomerId(String cusId);

    //    public List<Orders> getOrdersByCustomerId(String customerId) {
//        // Ensure that you pass the customer ID as a string parameter
//        return repo.findByCusID(customerId);
//    }
    List<Orders> getOrdersByCustomerId(Customer customer);
}
