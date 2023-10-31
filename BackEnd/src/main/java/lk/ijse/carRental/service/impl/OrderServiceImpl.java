package lk.ijse.carRental.service.impl;


import lk.ijse.carRental.dto.CustomerDTO;
import lk.ijse.carRental.dto.OrderDetailsDTO;
import lk.ijse.carRental.dto.OrdersDTO;
import lk.ijse.carRental.entity.Car;
import lk.ijse.carRental.entity.Customer;
import lk.ijse.carRental.entity.OrderDetails;
import lk.ijse.carRental.entity.Orders;
import lk.ijse.carRental.repo.OrderRepo;
import lk.ijse.carRental.service.CarService;
import lk.ijse.carRental.service.CustomerService;
import lk.ijse.carRental.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    CustomerService service;

    @Autowired
    CarService carService;

    @Autowired
    OrderRepo repo;

    @Value("${upload.dir}")
    private String uploadDir;

    @Autowired
    ModelMapper mapper;


    @Override
    public void addOrder(OrdersDTO dto, OrderDetailsDTO orderDetailsDTO) {
        System.out.println(orderDetailsDTO + " " + dto);
        String bankReceiptImagePath = saveImage(dto.getBankReciept());

//        List<Orders> list = new ArrayList<>();
        Orders order = new Orders();
        order.setOid("O001");
        CustomerDTO customer = service.findCustomer(dto.getCustomerID());
//        order.setCusID(dto.getCustomerID());
        order.setCusID(mapper.map(customer, Customer.class));
        OrderDetails orderDetails = new OrderDetails( "ODO001",orderDetailsDTO.getPickupDate(), orderDetailsDTO.getReturnDate(), orderDetailsDTO.getPickupLocation(), orderDetailsDTO.getReturnLocation(), orderDetailsDTO.getDriverStatus(), orderDetailsDTO.getLoseDamage(), bankReceiptImagePath, orderDetailsDTO.getOrderStatus(), orderDetailsDTO.getCarRegNo());
        order.setOrderDetails(orderDetails);

        Orders save = repo.save(order);
//        order.setCustomerID(mapper.map(service.findCustomer(dto.getCustomerID()), Customer.class));
//
//        order.setOrderDetails(orderDetails);
//        System.out.println(order);
//
//        OrderDetails map = new OrderDetails();
//
//        map.setBankReciept(bankReceiptImagePath);
//
//        Orders orders = new Orders();
//        CustomerDTO customerDto = service.findCustomer(dto.getNic());
//        Customer cus = mapper.map(customerDto, Customer.class);
//
//        orders.setCustomerID(cus);
//        List<OrderDetails> details=new ArrayList<>();
//        details.add(map);
//
//        orders.setOrderDetails(details);


    }


    private String saveImage(MultipartFile image) {

        // Validate the uploaded file
        if (image.isEmpty()) {
            throw new IllegalStateException("The provided image is empty!");
        }

        // Ensure the upload directory exists. If not, create it.
        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Create a sanitized filename
        String fileName = StringUtils.cleanPath(image.getOriginalFilename());

        // Build the path using the uploadDir and fileName
        Path copyLocation = Paths.get(uploadDir, fileName);

        try {
            // Save the uploaded file to the designated directory
            Files.copy(image.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Could not store file " + fileName + ". Please try again!", e);
        }

        // Return only the filename, not the full path
        return fileName;


    }

}