package com.example.tcc.service;

import com.example.tcc.entity.BaseTransaction;
import com.example.tcc.entity.Result;
import com.example.tcc.entity.User;
import com.example.tcc.entity.UserBuyRequest;
import com.example.tcc.service.base.BaseUserTransactionService;
import org.springframework.beans.BeanUtils;

public class BuyService extends BaseUserTransactionService {
    
    public void userBuy(UserBuyRequest userBuyRequest) throws Exception {
        User user = new User();
        BeanUtils.copyProperties(userBuyRequest, user);
        user.setMoney(user.getMoney() - userBuyRequest.getCost());
        this.process(user, userBuyRequest.getRequestId());
    }
    
    
    @Override
    public Result tryBusiness(BaseTransaction transaction, User user) {
        return Result.builder().status("success").build();
    }

    @Override
    public User confirmBusiness(BaseTransaction transaction, User user, Result result) {
        return null;
    }

    @Override
    public User cancelBusiness(BaseTransaction transaction, User user, Result result) {
        return null;
    }
}
