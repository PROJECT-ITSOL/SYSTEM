package com.example.sell.model.dto;

import com.example.sell.data.model.Order;
import com.example.sell.data.model.Product;
import com.example.sell.data.model.ProductReturn;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductReturnDTO {
    private int idProductReturn;
    private String idProduct;
    private Product productReturn;
    private String idOrder;
    private Order orderFail;
    private int amount;
    private String status;


    public  ProductReturnDTO convertProductReturn(ProductReturn productReturn){
        ProductReturnDTO productReturnDTO=new ProductReturnDTO();

            productReturnDTO.setIdProductReturn(productReturn.getIdProductReturn());
            productReturnDTO.setIdProduct(productReturn.getIdProduct());
            productReturnDTO.setIdOrder(productReturn.getIdOrder());
            productReturnDTO.setProductReturn(getProductReturn());
            productReturnDTO.setAmount(productReturn.getAmount());
            productReturnDTO.setStatus(productReturn.getStatus());
            productReturnDTO.setOrderFail(productReturn.getOrderFail());
        return productReturnDTO;
    }
}
