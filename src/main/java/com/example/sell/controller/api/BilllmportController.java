package com.example.sell.controller.api;

import com.example.sell.model.resutlData.BaseApiResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/billImport")
public class BilllmportController {

    @GetMapping("/fake")
    public BaseApiResult fakeData(){
        BaseApiResult result = new BaseApiResult();

        return result;
    }
}
