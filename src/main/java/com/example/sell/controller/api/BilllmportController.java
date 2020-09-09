package com.example.sell.controller.api;

import com.example.sell.constanst.RandomData;
import com.example.sell.data.model.BillImport;
import com.example.sell.data.service.BilllmportService;
import com.example.sell.model.resutlData.BaseApiResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/billImport")
public class BilllmportController {

    private static final Logger logger = LogManager.getLogger(BilllmportController.class);

    @Autowired
    private BilllmportService billlmportService;

    @GetMapping("/fake")
    public BaseApiResult fakeData(){
        BaseApiResult result = new BaseApiResult();
        int total = billlmportService.getTotalBillImport();
        List<BillImport> billImports = new ArrayList<>();

        try {

            for (int i=total+1;i<total+10;i++){
                BillImport billImport = new BillImport();
                billImport.setIdBillImport(new RandomData().randomText(6));
                billImport.setCreateDate(new Date());
                billImport.setTotalMoney((double) new Random().nextInt(100));
                billImports.add(billImport);
            }
            billlmportService.addNewList(billImports);
            result.setSuccess(true);
            result.setMessage("Fake List Success!");
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            logger.error(e.getMessage());
        }
        return result;
    }

    @GetMapping("/list")
    public List<BillImport> getList(){
        return billlmportService.getList();
    }
}
