package com.example.demo.service;



import com.example.demo.model.PizzaOrder;
import com.example.demo.repos.PizzaOrderRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PizzaOrderService {

    private final PizzaOrderRepo orderRepo;

    public PizzaOrderService(PizzaOrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    public void deleteOrderById(Long id) {
        orderRepo.deleteById(id);
    }

    public List<PizzaOrder> getAllOrders() {
        return StreamSupport.stream(orderRepo.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }
}
