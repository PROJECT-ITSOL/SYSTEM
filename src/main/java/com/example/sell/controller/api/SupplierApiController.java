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

    @GetMapping("/{id}")
    public Supplier getSupplierById(@PathVariable int id) {

        return supplierService.getSupplierById(id);
    }

    @DeleteMapping("/delete")
    public BaseApiResult delete(@RequestParam(value = "id", required = true) int id) {
        BaseApiResult result = new BaseApiResult();
        if (supplierService.deleteSupplierById(id)) {
            result.setSuccess(true);
            result.setMessage("Delete success");
        } else {
            result.setSuccess(false);
            result.setMessage("Delete fail");
        }
        return result;
    }

    @GetMapping("/status")
    public List<Supplier> getSupplierByStatus(@RequestParam(value = "status", required = true) boolean status) {
        return supplierService.getListSupplierByStatus(status);
    }

    @PostMapping("/addSupplier")
    public BaseApiResult addNewSupplier(@RequestBody Supplier supplier) {
        BaseApiResult result = new BaseApiResult();
        try {
            supplierService.addNewSupplier(supplier);
            result.setSuccess(true);
            result.setMessage("Success add new supplier !");
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage("Fail to add new supplier!");
        }
        return result;

    }

    @PutMapping("/update/{id}")
    public BaseApiResult updateSupplier(@PathVariable int id, @RequestBody Supplier supplier) {
        BaseApiResult result = new BaseApiResult();
        Supplier sup = supplierService.findOne(id);
        sup.setAddress(supplier.getAddress());
        sup.setLogo(supplier.getLogo());
        sup.setName(supplier.getName());
        sup.setPhoneNumber(supplier.getPhoneNumber());
        sup.setStatus(supplier.getStatus());

        try {
            supplierService.addNewSupplier(sup);
            result.setMessage("Update success");
            result.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            result.setMessage("Update fail");
            result.setSuccess(false);
        }
        return result;
    }
}









