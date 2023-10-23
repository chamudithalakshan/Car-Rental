package lk.ijse.carRental.service.impl;

import lk.ijse.carRental.advisor.FileStorageException;
import lk.ijse.carRental.dto.CustomerDTO;
import lk.ijse.carRental.dto.CustomerResponseDTO;
import lk.ijse.carRental.entity.Customer;
import lk.ijse.carRental.repo.CustomerRepo;
import lk.ijse.carRental.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    ModelMapper mapper;

    @Value("${upload.dir}")
    private String uploadDir;


    @Override
    public void addCustomer(CustomerDTO dto, MultipartFile nicImage, MultipartFile drivingLicenseImage) {

        if (customerRepo.existsById(dto.getNicNumber())) {
            throw new IllegalStateException("A customer with the provided NIC already exists!");
        }

        String nicImagePath = saveImage(nicImage);
        String drivingLicenseImagePath = saveImage(drivingLicenseImage);

        Customer customer = mapper.map(dto, Customer.class);
        customer.setNicImage(nicImagePath);
        customer.setDrivingLicenseImage(drivingLicenseImagePath);

        customerRepo.save(customer);
    }

    @Override
    public void deleteCustomer(String id) {

    }

    @Override
    public List<CustomerResponseDTO> getAllCustomer() {
        List<Customer> customerEntities = customerRepo.findAll();

        return customerEntities.stream()
                .map(customer -> {
                    CustomerResponseDTO responseDTO = mapper.map(customer, CustomerResponseDTO.class);

                    // Assuming your DTOs contain the full image URL and your entities only have the path:
                    responseDTO.setNicImage("http://localhost:8080/BackEnd_war/customer/image/" + customer.getNicImage());
                    System.out.println("Customer NIC path=  "+customer.getNicImage());
                    responseDTO.setDrivingLicenseImage("http://localhost:8080/BackEnd_war/customer/image/" + customer.getDrivingLicenseImage());

                    return responseDTO;
                })
                .collect(Collectors.toList());

    }


    @Override
    public CustomerDTO findCustomer(String id) {
        return null;
    }

    @Override
    public void updateCustomer(CustomerDTO c) {

    }

    @Override
    public boolean updateCustomerStatus(String nic, String status) {
        Optional<Customer> optionalCustomer = customerRepo.findById(nic);

        if (!optionalCustomer.isPresent()) {
            return false;
        }

        Customer customer = optionalCustomer.get();
        customer.setActiveStatus(status);

        customerRepo.save(customer);
        return true;
    }





    @Override
    public List<Customer> getAllCustomers() {
        return null;
    }

    @Override
    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = Paths.get(uploadDir).resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if(resource.exists()) {
                return resource;
            } else {
                throw new FileStorageException("File not found: " + fileName);  // You can define a custom exception or use a built-in one
            }
        } catch (MalformedURLException ex) {
            throw new FileStorageException("File not found: " + fileName, ex);  // Similarly, handle the exception appropriately
        }
    }


    @Autowired
    private Environment env;

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
