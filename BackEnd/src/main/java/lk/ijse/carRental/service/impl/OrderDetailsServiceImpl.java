package lk.ijse.carRental.service.impl;

import lk.ijse.carRental.entity.OrderDetails;
import lk.ijse.carRental.repo.OrderDetailsRepo;
import lk.ijse.carRental.service.OrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class OrderDetailsServiceImpl implements OrderDetailsService {

    @Autowired
    private OrderDetailsRepo orderDetailsRepository;

    @Override
    public List<OrderDetails> getAllOrderDetails() {
        return orderDetailsRepository.findAll();
    }


}
