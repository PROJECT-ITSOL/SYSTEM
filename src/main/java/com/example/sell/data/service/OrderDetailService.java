
package com.example.sell.data.service;

import com.example.sell.data.model.OrderDetail;
import com.example.sell.data.repository.OrderDetailRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderDetailService {
    private static final Logger logger = LogManager.getLogger(OrderDetailService.class);
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    public List<OrderDetail> getOrderDetailsByIdOrder(String idOrder) {
        try {
            return orderDetailRepository.getOrderDetailByIdOrder(idOrder);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ArrayList<>();
        }
    }

    public void addNewListOrderDetail(List<OrderDetail> orderDetails) {
        orderDetailRepository.saveAll(orderDetails);
    }

    public int getTotailOrderDetail() {
        return orderDetailRepository.getTotailOrderDetail();
    }

    public Page<OrderDetail> getPageListOrdersDetail(int pageNo, int pageSize) {
        return orderDetailRepository.findAll(PageRequest.of(pageNo, pageSize));
    }

    //public Object getPageListOdersDetail(int pageNo, int pageSize) {

    //}
//    public int getTotailOrderDetail() {
//    }
}
