package com.example.sell.controller.api;

import com.example.sell.data.model.BillImportDetail;
import com.example.sell.data.service.BillImportDetailService;
import com.example.sell.model.resutlData.BaseApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.sell.model.dto.BillImportDetailDTO;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/billDetail")
public class BillImportDetailApiController {

    @Autowired
    private BillImportDetailService billImportDetailService;



    @GetMapping("")
    public List<BillImportDetailDTO> getListBillByIdBillImport(@RequestParam(value = "idBillImport",required = true)String id){
        return billImportDetailService.getListByIdBill(id);
    }

//    @GetMapping("/all")
//    public List<BillImportDetailDTO> getListBillDetail(){
//        return billImportDetailService.getAllBill();
//    }

    @PostMapping("/addNewBillDetail")
    public BaseApiResult addNewBillDetail(@RequestBody BillImportDetailDTO billImportDetailDTO){
        BaseApiResult baseApiResult = new BaseApiResult();
        BillImportDetail billImportDetail = new BillImportDetail();
        billImportDetail.setPrice(billImportDetailDTO.getPrice());
        billImportDetail.setAmount(billImportDetailDTO.getAmount());
        billImportDetail.setProductImport(billImportDetailDTO.getProduct());
        billImportDetail.setBillImport(billImportDetailDTO.getBillImport());
        billImportDetail.setTotalPrice(billImportDetailDTO.getPrice()*billImportDetailDTO.getAmount());
        if(billImportDetailService.addNewBillDetail(billImportDetail)){
            baseApiResult.setSuccess(true);
            baseApiResult.setMessage("Success to add new Bill Import !");
        }else {
            baseApiResult.setSuccess(false);
            baseApiResult.setMessage("Fail to add new Bill Import !");
        }
        return baseApiResult;

    }

    @PutMapping("update/{id}")
    public BaseApiResult editBillDetail(@RequestBody BillImportDetail billImportDetail,
                                        @PathVariable int id){
        BaseApiResult baseApiResult = new BaseApiResult();
        BillImportDetail billImportDetail1 = billImportDetailService.getBillDetailById(id);
        billImportDetail1.setAmount(billImportDetail.getAmount());
        billImportDetail1.setProductImport(billImportDetail.getProductImport());
        billImportDetail1.setIdProduct(billImportDetail.getIdProduct());
        billImportDetail1.setIdBillImport(billImportDetail.getIdBillImport());
        if(billImportDetailService.addNewBillDetail(billImportDetail1)){
            baseApiResult.setSuccess(true);
            baseApiResult.setMessage("Success to update Bill Import !");
        }else {
            baseApiResult.setSuccess(false);
            baseApiResult.setMessage("Fail to update Bill Import !");
        }
        return baseApiResult;
    }

    @DeleteMapping("delete/{id}")
    public BaseApiResult deleteBillDetail(@PathVariable int id){
        BaseApiResult baseApiResult = new BaseApiResult();
        if(billImportDetailService.deleteBillDetail(id)){
            baseApiResult.setSuccess(true);
            baseApiResult.setMessage("Success to delete Bill Import !");
        }else {
            baseApiResult.setSuccess(false);
            baseApiResult.setMessage("Fail to delete Bill Import !");
        }
        return baseApiResult;
    }


}
