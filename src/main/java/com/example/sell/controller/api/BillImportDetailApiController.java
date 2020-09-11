package com.example.sell.controller.api;

import com.example.sell.data.model.BillImportDetail;
import com.example.sell.data.service.BillImportDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/billDetail")
public class BillImportDetailApiController {

    @Autowired
    private BillImportDetailService billImportDetailService;

    @GetMapping("")
    public List<BillImportDetail> getListBillByIdBillImport(@RequestParam(value = "idBillImport",required = true)String id){
        return billImportDetailService.getBillByIdBillImport(id);
    }

    @GetMapping("/all")
    public List<BillImportDetail> getListBill(){
        return billImportDetailService.getBill();
    }
}
