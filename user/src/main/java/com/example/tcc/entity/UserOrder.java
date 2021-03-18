package com.example.tcc.entity;

import com.example.tcc.entity.transaction.BaseTarget;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserOrder extends BaseTarget {

    private String id;
    
    private String type;

    private String userId;

    private String goodId;

    private Integer nums;

    private String status;

    public void save() {

    }

    public void waitForTransfer() {
    }

    public void fail() {
    }
}
