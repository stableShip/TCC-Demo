package com.example.tcc.service;

import com.example.tcc.entity.*;
import com.example.tcc.entity.transaction.BaseTransaction;
import com.example.tcc.service.base.BaseGoodOrderTransactionService;
import org.springframework.beans.BeanUtils;

import java.util.UUID;

/**
 * 商品下单服务
 */
public class ReturnGoodService extends BaseGoodOrderTransactionService {

    private String transactionType = "returnGood";

    public void returnGood(ReturnGoodRequest order) throws Exception {
        this.process(order, order.getRequestId());
    }

    @Override
    public Result tryBusiness(BaseTransaction transaction, GoodOrder goodOrder) {
        // 检查商品退货申请是否已经通过
       
        // 生成退货订单
        GoodOrder newGoodOrder = new GoodOrder();
        BeanUtils.copyProperties(goodOrder, newGoodOrder);
        newGoodOrder.setStatus("returnGood");
        newGoodOrder.setId(UUID.randomUUID().toString());
        // 请求扣减用户资产， 扣减成功--扣减商品数量， 扣减失败--事务失败，订单失败，

        return Result.builder().status("success").build();
    }

    @Override
    public GoodOrder confirmBusiness(BaseTransaction transaction, GoodOrder goodOrder, Result result) {
        // 增加商品数量 
        
        // 商品订单退货成功，转为已退货
        goodOrder.setStatus("returned");
        goodOrder.save();
        // 事务数据更新已经由基类处理了
        return goodOrder;
    }

    @Override
    public GoodOrder cancelBusiness(BaseTransaction transaction, GoodOrder goodOrder, Result result) {
        // 退货失败
        goodOrder.setStatus("returnFail");
        goodOrder.save();
        // 事务数据更新已经由基类处理了
        return goodOrder;
    }
}
