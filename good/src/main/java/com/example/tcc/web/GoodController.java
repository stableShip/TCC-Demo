package com.example.tcc.web;

import com.example.tcc.service.base.TransactionService;
import com.sun.org.apache.xpath.internal.axes.HasPositionalPredChecker;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class GoodController {


    /**
     * 下单
     *
     * @return
     */
    @PostMapping("/orderGood")
    public Map orderGood() {
        return null;
    }

    /**
     * 定时获取是否有待处理的事务, 发送邮件通知
     * @return
     */
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
    
    
    public Map retryTransaction(Map<String, String> data){
        return new HashMap();

    }

}
