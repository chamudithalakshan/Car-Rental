package lk.ijse.carRental.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lk.ijse.carRental.dto.CustomerDTO;
import lk.ijse.carRental.dto.OrderDetailsDTO;
import lk.ijse.carRental.dto.OrdersDTO;
import lk.ijse.carRental.repo.OrderRepo;
import lk.ijse.carRental.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

}
