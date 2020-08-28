package com.example.sell.controller.api;


import com.example.sell.data.model.Supplier;
import com.example.sell.data.repository.SupplierRepository;
import com.example.sell.data.service.SupplierService;
import com.example.sell.model.api.BaseApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/supplier")
public class SupplierApiController {

    @Autowired
    private SupplierService supplierService;

    @GetMapping("")

    public ResponseEntity<?> getListSupplier() {
        List<Supplier> suppliers = supplierService.getListSupplier();
        return ResponseEntity.ok(suppliers);
    }

    @GetMapping("/idSupplier")
    public  Supplier getSupplierById(@RequestParam(value = "id",required = true) int id){

            return supplierService.getSupplierById(id);
    }

    @DeleteMapping("/delete/{id}")
    public BaseApiResult delete(@PathVariable int id) {
        BaseApiResult result = new BaseApiResult();
        if (supplierService.deleteSupplierById(id)){
            result.setSuccess(true);
            result.setMessage("Delete success");
        } else {
            result.setSuccess(false);
            result.setMessage("Delete fail");
        }
        return result;
    }

    @GetMapping("/status")
    public List<Supplier> getSupplierByStatus(@RequestParam(value = "status",required = true) boolean status){
        return supplierService.getListSupplierByStatus(status);
    }




}
