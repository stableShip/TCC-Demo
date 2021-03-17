package com.example.tcc.service.base;

import com.example.tcc.entity.BaseTransaction;
import com.example.tcc.entity.Result;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Service
@Slf4j
public abstract class TransactionService<T> {

    private String BaseTransactionType;


    /**
     * 自检
     */
    public void selfCheck(T t) throws Exception {
        if (!StringUtils.hasLength(this.getBaseTransactionType())) {
            throw new Exception("this.BaseTransactionType 不能为空");
        }
    }

    private BaseTransaction beginTransaction(T t, String requestId) throws Exception {
        log.info("begin BaseTransaction, BaseTransaction type: {}, requestId: {}", this.BaseTransactionType, requestId);
        this.selfCheck(t);
        if (!StringUtils.hasLength(requestId)) {
            throw new Exception("请求id不能为空");
        }
        // 根据requestId检测事务是否存在，不存在则创建新的事务
        BaseTransaction newTransaction = new BaseTransaction();
        newTransaction.setRequestId(requestId);
        newTransaction.setType(this.BaseTransactionType);
        newTransaction.setStatus("Pending");
        return newTransaction;
    }

    protected T process(T t, String uniqRequestId) throws Exception {
        Result result;
        BaseTransaction transaction = this.beginTransaction(t, uniqRequestId);
        try {
            result = this.tryBusiness(transaction, t);
        } catch (Exception ex) {
            String msg = ex.getMessage();
            if (msg == null) {
                msg = ex.getMessage();
            }
            log.error(msg, ex, ex.getMessage(), ex.getMessage());
            transaction.fail(msg);
            throw ex;
        }
        return this.handleBusiness(transaction, t, result);
    }


    /**
     * 成功失败的处理
     */
    protected T handleBusiness(BaseTransaction transaction, T t, Result result) {
        if (result.getStatus() == "success") {
            return this.successCallback(transaction, t, result);
        } else if (result.getStatus() == "fail") {
            return this.failCallback(transaction, t, result);
        }
        return t;
    }

    private T successCallback(BaseTransaction transaction, T t, Result result) {
        this.BaseTransactionDone(transaction);
        return this.confirmBusiness(transaction, t, result);
    }

    private T failCallback(BaseTransaction transaction, T t, Result result) {
        this.BaseTransactionFail(transaction);
        return this.cancelBusiness(transaction, t, result);
    }

    /**
     * 子类实现的业务逻辑
     * 一般是准备数据，然后请求第三方服务
     */
    public abstract Result tryBusiness(BaseTransaction transaction, T t);

    /**
     * 子类实现业务逻辑，在调用成功时执行。
     *
     * @param result
     * @abstract
     */
    public abstract T confirmBusiness(BaseTransaction transaction, T t, Result result);

    /**
     * 子类实现业务逻辑，在调用失败时执行。
     *
     * @param result
     * @abstract
     */
    public abstract T cancelBusiness(BaseTransaction transaction, T t, Result result);


    private BaseTransaction BaseTransactionDone(BaseTransaction transaction) {
        transaction.setStatus("done");
        transaction.save();
        return transaction;
    }

    private BaseTransaction BaseTransactionFail(BaseTransaction transaction) {
        transaction.setStatus("fail");
        transaction.save();
        return transaction;
    }


}
