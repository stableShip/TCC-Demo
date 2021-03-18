package com.example.tcc.service;

import com.example.tcc.entity.*;
import com.example.tcc.entity.transaction.BaseTransaction;
import com.example.tcc.service.base.BaseGoodOrderTransactionService;
import org.springframework.beans.BeanUtils;

/**
 * 商品下单服务
 */
public class OrderGoodService extends BaseGoodOrderTransactionService {

    private String transactionType = "OrderGood";

    public void orderGood(OrderGoodRequest goodRequest) throws Exception {
        this.process(goodRequest, goodRequest.getRequestId());
    }

    @Override
    public Result tryBusiness(BaseTransaction transaction, GoodOrder goodOrder) {
        // if(user.money -=100)     try. 
        //  good.nums -=1   comfirm
        // return 'success'
        
        // 检查商品数量, 分布式锁， 锁同一个商品
        Good storedGood = Good.getGood(goodOrder.getGoodId());
        if (storedGood.getNums() < goodOrder.getNums()) {
            return Result.builder().error(new Exception("商品数量不足")).status("fail").build();
        }
        // 生成用户订单，状态为待付款
        GoodOrder newOrder = new GoodOrder();
        BeanUtils.copyProperties(goodOrder, newOrder);
        newOrder.setStatus("unPaid");
        newOrder.save();

        // 请求扣减用户资产， 扣减成功--扣减商品数量， 扣减失败--事务失败，订单失败，

        return Result.builder().status("success").build();
    }

    @Override
    public GoodOrder confirmBusiness(BaseTransaction transaction, GoodOrder goodOrder, Result result) {
        // 扣减用户购买数量 
        Good storedGood = Good.getGood(goodOrder.getGoodId());
        storedGood.setNums(storedGood.getNums() - goodOrder.getNums());
        storedGood.save();
        // 更新用户订单为 待发货
        goodOrder.waitForTransfer();
        goodOrder.save();
        // 事务数据更新已经由基类处理了
        return goodOrder;
    }

    @Override
    public GoodOrder cancelBusiness(BaseTransaction transaction, GoodOrder goodOrder, Result result) {
        // 下单订单失败
        goodOrder.fail();
        goodOrder.save();
        // 事务数据更新已经由基类处理了
        return goodOrder;
    }
}
