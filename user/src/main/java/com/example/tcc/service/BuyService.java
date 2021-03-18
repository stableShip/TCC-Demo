package com.example.tcc.service;

import com.example.tcc.entity.Result;
import com.example.tcc.entity.User;
import com.example.tcc.entity.UserBuyRequest;
import com.example.tcc.entity.UserOrder;
import com.example.tcc.entity.transaction.BaseTransaction;
import com.example.tcc.service.base.BaseUserOrderTransactionService;

public class BuyService extends BaseUserOrderTransactionService {

    public void userBuy(UserBuyRequest userBuyRequest) throws Exception {
        this.process(userBuyRequest, userBuyRequest.getRequestId());
    }

    @Override
    public Result tryBusiness(BaseTransaction transaction, UserOrder userOrder) {
        return null;
    }

    @Override
    public UserOrder confirmBusiness(BaseTransaction transaction, UserOrder userOrder, Result result) {
        return null;
    }

    @Override
    public UserOrder cancelBusiness(BaseTransaction transaction, UserOrder userOrder, Result result) {
        return null;
    }
}
