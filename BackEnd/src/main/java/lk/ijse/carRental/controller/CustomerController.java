package lk.ijse.carRental.controller;

import lk.ijse.carRental.dto.CustomerDTO;
import lk.ijse.carRental.dto.CustomerResponseDTO;
import lk.ijse.carRental.entity.Customer;
import lk.ijse.carRental.service.CustomerService;
import lk.ijse.carRental.util.ResponseUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customer")
@CrossOrigin
public class CustomerController {
    @Autowired
    CustomerService service;

    @Autowired
    ModelMapper modelMapper;

    @Value("${upload.dir}")
    private String uploadDir;

    @PostMapping
    public ResponseEntity<String> addCustomer(
            @ModelAttribute CustomerDTO dto,
            @RequestParam("nicImage") MultipartFile nicImage,
            @RequestParam("drivingLicenseImage") MultipartFile drivingLicenseImage) {

        service.addCustomer(dto, nicImage, drivingLicenseImage);
        return new ResponseEntity<>("Successfully Added", HttpStatus.OK);
    }
    @DeleteMapping(params = {"id"})
    public ResponseUtil deleteCustomer(String id){
        service.deleteCustomer(id);
        return new ResponseUtil("Ok","Successfully Deleted",id);
    }

//    @GetMapping
//    public ResponseUtil getAllCustomer(){
//
////        return new ResponseUtil("Ok","Successfully Loaded",service.getAllCustomer());
//
//        List<CustomerResponseDTO> customers = service.getAllCustomer();
//        return new ResponseUtil("Ok", "Successfully Loaded", customers);
//    }

    @GetMapping
    public ResponseEntity<List<CustomerResponseDTO>> getAllCustomers() {
        List<CustomerResponseDTO> response = service.getAllCustomer();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(params = {"id"})
    public ResponseUtil findCustomer(String id){
        return new ResponseUtil("Ok","Successfull", service.findCustomer(id));
    }

    @PutMapping
    public ResponseUtil updateCustomer(@RequestBody CustomerDTO c){
        service.updateCustomer(c);
        return new ResponseUtil("Ok","Successfully Updated",c);
    }

    // Serve image
    @GetMapping("/image/{filename:.+}")
    public ResponseEntity<Resource> serveImage(@PathVariable String filename) {
        Resource file = service.loadFileAsResource(filename);
        if (file.exists()) {
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                    .body(file);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }




}
