package com.example.sell.controller.api;

import com.example.sell.data.model.BillImport;
import com.example.sell.data.model.BillImportDetail;
import com.example.sell.data.model.Product;
import com.example.sell.data.service.BilllmportDetailService;
import com.example.sell.data.service.BilllmportService;
import com.example.sell.data.service.ProductService;
import com.example.sell.model.resutlData.BaseApiResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/billdetail")
public class BilllmportDetailController {
    private static final Logger logger = LogManager.getLogger(BilllmportDetailController.class);

    @Autowired
    private BilllmportDetailService billlmportDetailService;

    @Autowired
    private ProductService productService;

    @Autowired
    private BilllmportService billlmportService;

    @GetMapping("/fake")
    public BaseApiResult fakeData(){
        BaseApiResult result = new BaseApiResult();
        List<BillImport> billImports= billlmportService.getList();
        List<Product> productList=productService.findAll();
        List<BillImportDetail> billImportDetails=new ArrayList<>();
        try {
            billImports.stream().forEach(billImport -> {
                for (int i = 0; i < 2; i++) {
                    BillImportDetail billImportDetail = new BillImportDetail();
    //                billImportDetail.set
                    billImportDetail.setAmount(new Random().nextInt(10));
                    billImportDetail.setBillImport(billImport);
                    billImportDetail.setProductImport(productList.get(new Random().nextInt(productList.size())));
                    billImportDetail.setPrice((double) new Random().nextInt(20));
                    billImportDetails.add(billImportDetail);
                }
                billlmportDetailService.addNewList(billImportDetails);
                result.setSuccess(true);
                result.setMessage("Fake List Success!");
            });
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            logger.error(e.getMessage());
        }
//        int total = s
        return result;
    }

    @GetMapping("/list")
    public List<BillImportDetail> getList(){
        return billlmportDetailService.getList();
    }
}
