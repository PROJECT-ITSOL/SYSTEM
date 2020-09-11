package com.example.sell.controller.api;


import com.example.sell.data.model.BillImport;
import com.example.sell.data.service.BillImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("BillImport")
public class BillImportApiController {

    @Autowired
    private BillImportService billImportService;

    @GetMapping("")
    public List<BillImport> getAllBillImport(){
        return billImportService.getAllBillImport();
    }
}
