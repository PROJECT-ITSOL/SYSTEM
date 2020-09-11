package com.example.sell.model.api;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseApiResult {
    private boolean isSuccess;
    private String message;
}
