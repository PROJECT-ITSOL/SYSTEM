package com.example.sell.controller.api;


import com.example.sell.data.model.BillImport;
import com.example.sell.data.service.BillImportService;
import com.example.sell.model.resutlData.BaseApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("BillImport")
public class BillImportApiController {

    @Autowired
    private BillImportService billImportService;

    //Lấy tất cả bill
    @GetMapping("/all")
    public List<BillImport> getAllBillImport(){
        return billImportService.getAllBillImport();
    }

    //Lấy bill theo page
    @GetMapping("")
    public Page<BillImport> getListBillPage(@RequestParam(value = "page") int page){
        Pageable pageable =  PageRequest.of(page,5);
        Page<BillImport> listBillPage = billImportService.getAllPage(pageable);
        return listBillPage;

    }

    //Lấy bill theo id
    @GetMapping("/{id}")
    public BillImport getBillById(@PathVariable String id){
        return billImportService.getBillImportById(id);
    }

    //Thêm mới bill
    @PostMapping("/addBillImport")
    public BaseApiResult addNewBill(@RequestBody BillImport billImport){
        BaseApiResult baseApiResult = new BaseApiResult();
        try {
            billImportService.addNewBillImport(billImport);
            baseApiResult.setSuccess(true);
            baseApiResult.setMessage("Success add new Bill Import !");
        } catch (Exception e) {
            e.printStackTrace();
            baseApiResult.setSuccess(false);
            baseApiResult.setMessage("Fail to add new Bill Import!");
        }
        return baseApiResult;
    }

    //Sửa bill
    @PutMapping("/update/{id}")
    public BaseApiResult editBill(@RequestBody BillImport billImport,
                                  @PathVariable String id){
        BaseApiResult baseApiResult = new BaseApiResult();
        BillImport billImport1 = billImportService.getBillImportById(id);
        billImport1.setCreateDate(billImport.getCreateDate());
        try{
            billImportService.addNewBillImport(billImport1);
            baseApiResult.setMessage("Update success");
            baseApiResult.setSuccess(true);
        } catch (Exception e){
            e.printStackTrace();
            baseApiResult.setMessage("Update fail");
            baseApiResult.setSuccess(false);

        }
        return  baseApiResult;

    }


    //Xóa bill
    @DeleteMapping("delete/{id}")
    public BaseApiResult deleteBill(@PathVariable String id){
        BaseApiResult baseApiResult = new BaseApiResult();
        if(billImportService.deleteBillImportById(id)){
            baseApiResult.setMessage("Delete success");
            baseApiResult.setSuccess(true);
        } else {
            baseApiResult.setMessage("Delete fail");
            baseApiResult.setSuccess(false);

        }
        return  baseApiResult;
    }


}
