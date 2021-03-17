package com.example.tcc.entity;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Good {

    private String id;

    private String name;

    private Integer nums;
    
    public static Good getGood(String id){
        return Good.builder().id("1").nums(10).build(); 
    }
    
    public void save(){
        // save to db
    }

}
