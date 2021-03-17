package com.example.tcc.web;

import com.example.tcc.service.base.TransactionService;
import com.sun.org.apache.xpath.internal.axes.HasPositionalPredChecker;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class GoodController {

    @PostMapping("/pendingTransaction")
    public Map getPendingTransaction(){
        return null;
    }

    /**
     * 获取用户的商品数据流
     * @param data
     * @return
     */
    @PostMapping("/getUserGoodTransaction")
    public Map getUserGoodTransaction(Map<String, String> data){
        String orderId = data.get("orderId");
//        TransactionService.getTransactionByRelatedTargetId(orderId);
        return new HashMap();
    }

}
