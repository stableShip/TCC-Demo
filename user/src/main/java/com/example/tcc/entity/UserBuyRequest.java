package com.example.tcc.entity;

import lombok.Data;

@Data
public class UserBuyRequest extends UserOrder {
    
    private String requestId;
    
    private Integer cost;
    
}
