package com.example.sell.data.service;

import com.example.sell.data.model.Order;
import com.example.sell.data.model.Product;
import com.example.sell.data.model.ProductReturn;
import com.example.sell.data.repository.OrderRepository;
import com.example.sell.data.repository.ProductReturnRepository;
import com.example.sell.model.dto.ProductReturnDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductReturnService {
     static final Logger logger = LogManager.getLogger(ProductReturnService.class);

    @Autowired
    private ProductReturnRepository productReturnRepository;



    public void addNew(ProductReturn productReturn){
        productReturnRepository.save(productReturn);
    }

    @Transactional
    public void addNewList(List<ProductReturn> productReturns){
        productReturnRepository.saveAll(productReturns);
    }

//    public List<ProductReturnDTO> getAllListProductReturn(){
//
//        List<ProductReturnDTO> productReturnDTOS=new ArrayList<ProductReturnDTO>();
//        List<ProductReturn> productReturns= productReturnRepository.findAll();
//
//        try {
//            for(ProductReturn productReturn: productReturns){
//               //Product product= productReturn.getIdProduct();
//                Product product= productReturn.getProductReturn();
//                ProductReturnDTO productReturnDTO=new ProductReturnDTO().convertProductReturn(productReturn);
//
//                productReturnDTOS.add(productReturnDTO);
//            }
//            return productReturnDTOS;
//        } catch (Exception e) {
//            logger.error(e.getMessage());
//            return new ArrayList<>();
//        }
//    }

    public List<ProductReturn> getAllProductReturnListById(int id) {
        return  productReturnRepository.getProductReturnByIdOrder(id);
    }

    public ProductReturn getOne(int id){
        return productReturnRepository.findById(id).orElse(null);
    }

    public List<ProductReturn> getAllProductReturnList() {
        try{
            return  productReturnRepository.findAll();
        }catch (Exception e){
            return new ArrayList<>();
        }
    }

    public Page<ProductReturn> findAll(Pageable pageable) {
        Page<ProductReturn> productReturns= productReturnRepository.findAll(pageable);
        return  productReturns;
    }

    public ProductReturn getOrderById(int id) {
        return  productReturnRepository.findById(id).orElse(null);
    }

    public List<ProductReturn> getListOrderByStatus(String status) {
        return productReturnRepository.getProductReturnByIdOrder(status);
    }

    public boolean deleteProductReturn(int id) {
        try {
            productReturnRepository.deleteById(id);
            return true;

        } catch (Exception e){
            logger.error(e.getMessage());
            return false;
        }
    }

    public Boolean addNewProductReturn(ProductReturn productReturn) {
        try {

            productReturnRepository.save(productReturn);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Page<ProductReturn> searchProductReturnPage(Pageable pageable, String keyWord) {
        return productReturnRepository.getOrderByIdOrName(pageable,keyWord);
    }

//    public Page<ProductReturn> searchProductReturnStatusPage(Pageable pageable, boolean keyWord) {
//        return productReturnRepository.getProductReturnByIdStatus(pageable,keyWord);
//    }
}
