package com.example.tcc.entity.transaction;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseTransaction {

    private Integer id;
    
    private String relatedTargetId;

    private String requestId;

    private String parentTransactionId;

    private String type;

    private String status;

    private String remark;

    private String errorMsg;

    private String extend;

    public Boolean isDone() {
        return this.status == "done";
    }

    public Boolean isFail() {
        return this.status == "fail";
    }

    public BaseTransaction fail(String errorMsg) {
        this.setErrorMsg(errorMsg);
        this.setStatus("fail");
        return this;
    }

    public BaseTransaction done() {
        this.setStatus("done");
        return this;
    }
    
    public void save(){
        // save to db
    }

}
