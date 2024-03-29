package com.ubb.licenta.service;

import com.ubb.licenta.model.Order;
import com.ubb.licenta.model.enums.Status;
import com.ubb.licenta.repository.OrderRepository;
import com.ubb.licenta.repository.ProductOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository repository;

    @Autowired
    private ProductOrderRepository productOrdersRepository;

    @Override
    public Order save(Order order) {
        productOrdersRepository.saveAll(order.getProductOrders());
        return repository.save(order);
    }

    @Override
    public List<Order> getOrders() {
        return repository.findAll();
    }

    @Override
    public Order update(Order order) {
        return repository.save(order);
    }

    @Override
    public void delete(Long orderId) {
        repository.deleteById(orderId);
        productOrdersRepository.deleteProductOrderById(orderId);
    }

    @Override
    public Order getOrderById(long id) {
        return repository.findOrderById(id);
    }

    @Override
    public List<Order> filterBy(String orderStatus, Long dateOfLastOrderAsTs, Boolean sortDescending) {
        // daca parametri sunt setati la null atunci ei nu au fost prezenti in requestul de la frontend
        Predicate<Order> filterByStatus = x -> false;

        if (orderStatus != null) {
            Status status = Status.valueOf(orderStatus);
            filterByStatus = filterByStatus
                    .or(x -> x.getStatus() != null && x.getStatus().equals(status));
        }

        Predicate<Order> filterByDate = x -> false;

        if (dateOfLastOrderAsTs == null) {
            dateOfLastOrderAsTs = 0L;
        }

        Long finalDateOfLastOrderAsTs = dateOfLastOrderAsTs;
        filterByDate = filterByDate
                .or(x -> x.getDateOfOrderAsTs() >= finalDateOfLastOrderAsTs);

        // TODO: change to and instead of or when predicate for date works
        Predicate<Order> mainPredicate = filterByStatus.or(filterByDate);

        List<Order> filteredOrders = repository.findAll()
                .stream()
                .filter(mainPredicate)
                .collect(Collectors.toList());

        if (sortDescending == null) {
            sortDescending = true;
        }

        if (sortDescending) {
            Collections.sort(filteredOrders, Collections.reverseOrder());
        } else {
            Collections.sort(filteredOrders);
        }

        return filteredOrders;
    }

    @Override
    public List<Order> filterBy(String orderStatus, Boolean sortDescending) {
        // daca parametri sunt setati la null atunci ei nu au fost prezenti in requestul de la frontend
        Predicate<Order> filterByStatus = x -> false;

        if (orderStatus != null) {
            Status status = Status.valueOf(orderStatus);
            filterByStatus = filterByStatus
                    .or(x -> x.getStatus() != null && x.getStatus().equals(status));
        }

        List<Order> filteredOrders = repository.findAll()
                .stream()
                .filter(filterByStatus)
                .collect(Collectors.toList());

        if (sortDescending == null) {
            sortDescending = true;
        }

        if (sortDescending) {
            Collections.sort(filteredOrders, Collections.reverseOrder());
        } else {
            Collections.sort(filteredOrders);
        }

        return filteredOrders;
    }

}
