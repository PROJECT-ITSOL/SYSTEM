package com.example.sell.controller.api;


import com.example.sell.data.model.Supplier;
import com.example.sell.data.service.SupplierService;
import com.example.sell.model.resutlData.BaseApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/supplier")
public class SupplierApiController {

    @Autowired
    private SupplierService supplierService;

    //Lấy tất cả supplier
    @GetMapping("")
    public List<Supplier> getListSupplier() {
        return supplierService.getAllListSuppliers();
    }

    //Lấy supplier theo page
    @GetMapping(value = "/list")
    public Page<Supplier> listSupplierPage(@RequestParam( value = "page") int page) {
        Pageable pageable =  PageRequest.of(page,5);
        Page<Supplier> listSupplierPage = supplierService.findAll(pageable);
        return listSupplierPage;
    }

    //Lấy supplier theo id
    @GetMapping("/{id}")
    public Supplier getSupplierById(@PathVariable int id) {
        return supplierService.getSupplierById(id);
    }

    //Lấy supplier theo status
    @GetMapping("/status")
    public Page<Supplier> getSupplierByStatus(@RequestParam( value = "page") int page,
                                              @RequestParam(value = "status") Boolean status) {
        Pageable pageable =  PageRequest.of(page,5);
        Page<Supplier> listSupplier = supplierService.getListSupplierByStatus(pageable,status);
        return listSupplier;
    }

    //Xóa supplier theo id
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

    //Thêm mới supplier
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


    //Cập nhật supplier
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


    //Tìm kiếm theo tên
    @GetMapping("/search")
    public Page<Supplier> searchSupplier(@RequestParam( value = "page") int page,
                                         @RequestParam(name = "name") String name)
                                         {
        Pageable pageable =  PageRequest.of(page,5);
        Page<Supplier> listSearch = supplierService.searchSupplierPage(pageable,name);
        return listSearch ;
    }

}









