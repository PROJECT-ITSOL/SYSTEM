package com.example.sell.model.dto;

import com.example.sell.data.model.Order;
import com.example.sell.data.model.OrderDetail;
import com.example.sell.data.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDTO {
    private int idOrderDetail;
    private String idOrder;
//    private String idProduct;
    private Product productOrderDetail;
    private int amount;

    public OrderDetailDTO converOrderDetail(OrderDetail orderDetail){
        OrderDetailDTO orderDetailDTO= new OrderDetailDTO();

            orderDetailDTO.setIdOrderDetail(orderDetail.getIdOrderDetail());
            orderDetailDTO.setIdOrder(orderDetail.getIdOrder());
            orderDetailDTO.setAmount(orderDetail.getAmount());
            orderDetailDTO.setProductOrderDetail(orderDetail.getProductOrderDetail());
            return orderDetailDTO;
    }

}
