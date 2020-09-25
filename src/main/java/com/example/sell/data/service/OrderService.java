package com.example.sell.data.service;

import com.example.sell.data.model.Category;
import com.example.sell.data.model.Order;
import com.example.sell.data.model.OrderDetail;
import com.example.sell.data.model.Supplier;
import com.example.sell.data.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
//import java.util.logging.LogManager;
//import java.util.logging.Logger;

@Service
public class OrderService {
    // ham logger dung de thong bao loi
    static final Logger loggle = LogManager.getLogger(OrderService.class);
    @Autowired
    private OrderRepository orderRepository;

    public  Page<Order> getPageListOders(int pageNo, int pageSize) {
        return orderRepository.findAll(PageRequest.of(pageNo, pageSize));
    }

    public  boolean deleteOrder(String id) {
        try {
            orderRepository.deleteById(id);
            return true;

        } catch (Exception e){
            loggle.error(e.getMessage());
            return false;
        }
    }


    public int getTotalOrder() {
        return orderRepository.getTotalOrder();
    }

    @Transactional
    public void addNewListOrder(List<Order> orders) {
        orderRepository.saveAll(orders);
    }

//    public Order findOne(String id) {
//    }

    public Boolean addNewOrder(Order order) {
        try {

            orderRepository.save(order);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Page<Order> getOrderByIdOrName(Pageable pageable, String keyWord) {
        return orderRepository.getOrderByIdOrName(pageable, keyWord);
    }

    public List<Order> getAllOrderList() {
        try{
            return  orderRepository.findAll();
        }catch (Exception e){
            return new ArrayList<>();
        }

    }

    public Order findOne(String id) {
        return orderRepository.findById(id).orElse(null);
    }

    public Page<Order> findAll(Pageable pageable) {
        Page<Order> listPageOrder= orderRepository.findAll(pageable);
        return listPageOrder;
    }

    public Order getOrderById(String id) {
        return  orderRepository.findById(id).orElse(null);
    }

    public List<Order> getListOrderByStatus(String status) {
        return orderRepository.getListOrderByStatus(status);
    }

//    public boolean deleteInBatch(String id) {
//        try {
//            orderRepository.deleteInBatch(id);
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }


    public Page<Order> searchOrderPage(Pageable pageable, String keyWord) {
        return orderRepository.getOrderByIdOrName(pageable,keyWord);
    }


    public void addNewOrderDetail(OrderDetail odrdl) {
    }

//    public boolean deleteOrderDetail(String id) {
//    }
}
