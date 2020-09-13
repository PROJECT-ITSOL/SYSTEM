package com.example.sell.controller.api;


import com.example.sell.data.model.BillImport;
import com.example.sell.data.model.Comment;
import com.example.sell.data.model.Supplier;
import com.example.sell.data.service.BillImportService;
import com.example.sell.model.dto.BillImportDTO;
import com.example.sell.model.dto.CommentDTO;
import com.example.sell.model.resutlData.BaseApiResult;
import com.example.sell.model.resutlData.DataApiResult;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/billImport")
public class BillImportApiController {

    private static final Logger logger = LogManager.getLogger(CommentApiController.class);

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

    @GetMapping("/billPage")
    public BaseApiResult getListBill(@RequestParam(value = "page") int page){
        DataApiResult result= new DataApiResult();
        try {
            List<BillImport> listBill =billImportService.getAllBillImport();
            for (BillImport billImport : listBill){
                billImport.setTotalMoney(billImportService.getTotalPrice(billImport.getIdBillImport()));
                billImport.setTotalProduct(billImportService.getTotalAmount(billImport.getIdBillImport()));
            }

            Pageable pageable = PageRequest.of(page,5);
            int start = (int) pageable.getOffset();
            int end = (start + pageable.getPageSize())>listBill.size() ? listBill.size() : (start + pageable.getPageSize());
            Page<BillImport> listBillPage1=new PageImpl<>(listBill.subList(start,end),pageable,listBill.size());
            result.setData(listBillPage1);
            result.setSuccess(true);
        } catch (Exception e) {
            result.setMessage(e.getMessage());
            result.setSuccess(false);
            logger.error(e.getMessage());
        }
        return result;
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

    //Search bill
    @GetMapping("/search")
    public Page<BillImport> searchBillImport(@RequestParam( value = "page") int page,
                                         @RequestParam(value = "keyWord") String keyWord){

        Pageable pageable =  PageRequest.of(page,5);
        Page<BillImport> listBill = billImportService.searchBillById(pageable,keyWord);
        return listBill  ;
    }

    @RequestMapping(value = "/getTotalPrice/{idBill}",method = RequestMethod.GET)
    public Double getTotalPrice(@PathVariable("idBill")String idBill){

        return billImportService.getTotalPrice(idBill);
    }


}
