package lk.ijse.carRental.service.impl;

import lk.ijse.carRental.advisor.FileStorageException;
import lk.ijse.carRental.dto.CustomerDTO;
import lk.ijse.carRental.entity.Customer;
import lk.ijse.carRental.repo.CustomerRepo;
import lk.ijse.carRental.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
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
    public List<CustomerDTO> getAllCustomer() {
        return null;
    }

    @Override
    public CustomerDTO findCustomer(String id) {
        return null;
    }

    @Override
    public void updateCustomer(CustomerDTO c) {

    }

    @Override
    public List<Customer> getAllCustomers() {
        return null;
    }

    @Autowired
    private Environment env;

    private String saveImage(MultipartFile image) {
        // Validate the uploaded file
        if (image.isEmpty()) {
            throw new IllegalStateException("The provided image is empty!");
        }

        // Ensure the upload directory exists and if not, create it.
        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Create a sanitized filename and build the path
        String fileName = StringUtils.cleanPath(image.getOriginalFilename());
        Path copyLocation = Paths.get(uploadDir, fileName);

        try {
            // Save the uploaded file to the designated directory
            Files.copy(image.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
            return copyLocation.toString();
        } catch (IOException e) {
            throw new RuntimeException("Could not store file " + fileName + ". Please try again!", e);
        }
    }


}
