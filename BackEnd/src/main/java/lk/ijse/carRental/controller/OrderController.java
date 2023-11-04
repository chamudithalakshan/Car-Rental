package lk.ijse.carRental.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lk.ijse.carRental.dto.CustomerDTO;
import lk.ijse.carRental.dto.OrderDetailsDTO;
import lk.ijse.carRental.dto.OrdersDTO;
import lk.ijse.carRental.entity.Customer;
import lk.ijse.carRental.entity.Orders;
import lk.ijse.carRental.repo.OrderRepo;
import lk.ijse.carRental.service.CustomerService;
import lk.ijse.carRental.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/Reservation")
@CrossOrigin
public class OrderController {

    @Autowired
    OrderRepo orderRepo;

    @Autowired
    ModelMapper mapper;

    @Value("${upload.dir}")
    private String uploadDir;

    @Autowired
    OrderService service;

    @Autowired
    CustomerService customerService;


    @PostMapping
    public void placeRent(
            @ModelAttribute OrdersDTO dto,
            @RequestParam("bankReciept") MultipartFile bankReceipt,
            @RequestParam("orderDetailsDTO") String orderDetailsJson
    ) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        OrderDetailsDTO orderDetailsDTO = objectMapper.readValue(orderDetailsJson, OrderDetailsDTO.class);

        service.addOrder(dto, orderDetailsDTO);
    }


    @GetMapping("/all")
    public List<Orders> getAllOrders() {
        return service.getAllOrders();
    }

    @GetMapping("/by-customer/{customerId}")
    public ResponseEntity<List<Orders>> getOrdersByCustomerId(@PathVariable String customerId) {
        // Fetch the customer entity by ID
        Customer customer = customerService.getCustomerById(customerId);

        if (customer == null) {
            return ResponseEntity.notFound().build(); // Customer not found
        }

        // Retrieve orders associated with the customer
        List<Orders> orders = service.getOrdersByCustomerId(customer);

        return ResponseEntity.ok(orders);
    }

}
