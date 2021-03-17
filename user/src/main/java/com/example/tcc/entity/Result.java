package com.example.tcc.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Result {
    private String status;

    private Exception error;
}
